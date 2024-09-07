package com.example.backendcoreservice.util;

import com.example.backendcoreservice.transformer.mapper.AbstractMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TestMapper extends AbstractMapper<TestEntity, TestDto> {

}
