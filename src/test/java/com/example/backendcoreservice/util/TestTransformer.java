package com.example.backendcoreservice.util;

import com.example.backendcoreservice.transformer.AbstractTransformer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TestTransformer implements AbstractTransformer<TestEntity, TestDto, TestMapper> {
    private final TestMapper testMapper;

    @Override
    public TestMapper getMapper() {
        return testMapper;
    }
}
