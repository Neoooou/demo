package com.example.app.beaninject;

import com.example.app.annotation.EnableCustomBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @author: neo.zr
 * @since: 2021/4/27
 */

@Configuration
public class ABeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //获取EnableCustoBean注释的属性
        final Map<String, Object> attributes = importingClassMetadata.getAnnotationAttributes(EnableCustomBean.class.getName());

        //获取包扫描
        ClassPathScanningCandidateComponentProvider pathScanningCandidateComponentProvider = new ClassPathScanningCandidateComponentProvider(false);

        LinkedHashSet<BeanDefinition> candidateComponents = new LinkedHashSet<>();

        for (String basePackages : (String[]) attributes.get("basePackages")) {
            candidateComponents.addAll(pathScanningCandidateComponentProvider.findCandidateComponents(basePackages));
        }

        //注册Bean
        for (BeanDefinition candidateComponent : candidateComponents) {
            registry.registerBeanDefinition(candidateComponent.getBeanClassName(), candidateComponent);
        }
    }
}
