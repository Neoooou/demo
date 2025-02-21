package com.example.app.advice;

import com.example.app.advice.annotations.OutPower;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @Author: Zhang Ran
 * @Date: Created on 2020/10/9
 */

@Aspect
@Component
public class AspectAdvice {

    // 将函数标记为切入点
    @Pointcut("execution(* com.example.app.service.IHelloWorld.sayHello(..))")
    public void sayHello(){ }

//    @Before("sayHello()")
//    public void doBefore(){
//        System.out.println("do before method execution");
//    }

//    @After("sayHello()")
//    public void doAfter(){
//        System.out.println("do after method execution");
//    }

//    @AfterThrowing("sayHello()")
//    public void doAfterThrowing(){
//        System.out.println("do after method throw an error");
//    }

//    @Pointcut("execution(* say(..))")
//    public void say(){
//
//    }
//    @Around("say()")
//    public Object sayAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        System.out.println("say around");
//        return proceedingJoinPoint.proceed();
//    }

    @Around("@annotation(outPower)")
    public Object doOutPower(ProceedingJoinPoint proceedingJoinPoint, OutPower outPower) throws Throwable{
        // get servlet request
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();

        Object retVal = proceedingJoinPoint.proceed();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String executorPath = className + "#"+methodName;

        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Class cls = signature.getReturnType();
        return executorPath + " got [return Type ("+cls.getName()+")] - " + retVal.toString();
    }
}
