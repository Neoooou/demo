package com.example.app.advice;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ranz
 * @Since: 2025/3/13
 */
@Aspect
@Component
@Slf4j
public class ServiceLogAdvice {
    private static final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    private static final Gson gson = new Gson();


    @Pointcut("@annotation(com.example.app.annotation.ServiceLog) || @within(com.example.app.annotation.ServiceLog)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object proceed(ProceedingJoinPoint joinPoint) throws Throwable {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        LogEntity logEntity = initLogEntity(signature, args);
        Object result;
        try {
            result = joinPoint.proceed();
            logEntity.setResult(gson.toJson(result));
            logEntity.setCostTime(stopwatch.elapsed(TimeUnit.MILLISECONDS));
        } catch (Throwable t) {
            logEntity.setError(t.getClass().getSimpleName() + " - " + t.getMessage());
            logEntity.setCostTime(stopwatch.elapsed(TimeUnit.MILLISECONDS));
            throw t;
        }
        log.info(logEntity.getMethod(), gson.toJson(logEntity), null);

        return result;
    }

    private LogEntity initLogEntity(Signature signature, Object[] args) {
        LogEntity logEntity = new LogEntity();

        try {
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();

            logEntity.setClazz(methodSignature.getDeclaringType().getSimpleName())
                    .setMethod(method.getName());

            String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
            Map<String, Object> argMap = Maps.newHashMap();
            if (args != null && args.length > 0 && args.length == parameterNames.length) {
                for (int i = 0; i < parameterNames.length; ++i) {
                    argMap.put(parameterNames[i], args[i]);
                }
                logEntity.setArguments(gson.toJson(argMap));
            }
        } catch (Throwable t) {
            log.warn("ServiceLogAdvice", t);
        }

        return logEntity;
    }


    static class LogEntity {
        String clazz;
        String method;
        Long costTime;
        String arguments;
        String result;
        String error;

        public String getClazz() {
            return clazz;
        }

        public LogEntity setClazz(String clazz) {
            this.clazz = clazz;
            return this;
        }

        public String getMethod() {
            return method;
        }

        public LogEntity setMethod(String method) {
            this.method = method;
            return this;
        }

        public Long getCostTime() {
            return costTime;
        }

        public LogEntity setCostTime(Long costTime) {
            this.costTime = costTime;
            return this;
        }

        public String getArguments() {
            return arguments;
        }

        public LogEntity setArguments(String arguments) {
            this.arguments = arguments;
            return this;
        }

        public String getResult() {
            return result;
        }

        public LogEntity setResult(String result) {
            this.result = result;
            return this;
        }

        public String getError() {
            return error;
        }

        public LogEntity setError(String error) {
            this.error = error;
            return this;
        }
    }

}
