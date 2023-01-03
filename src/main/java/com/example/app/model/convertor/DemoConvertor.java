package com.example.app.model.convertor;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @Author: neo.zr
 * @Created on: 2021/8/13
 */
@Mapper(componentModel = "spring")
public interface DemoConvertor {
    @Mapping(source = "AEnum.code", target = "code")
    public A convert(B b);

    public List<A> convertList(List<B> bs);
}
