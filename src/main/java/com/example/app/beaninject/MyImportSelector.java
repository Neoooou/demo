package com.example.app.beaninject;

import com.example.app.model.Employee;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: neo.zr
 * @Created on: 2021/11/22
 */

public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{Employee.class.getName()};
    }
}
