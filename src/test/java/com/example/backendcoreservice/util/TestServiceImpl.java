package com.example.backendcoreservice.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TestServiceImpl implements TestService {
    private final TestDao testDao;
    private final TestTransformer testTransformer;


    @Override
    public TestDao getDao() {
        return testDao;
    }

    @Override
    public TestTransformer getTransformer() {
        return testTransformer;
    }

    @Override
    public String getEntityName() {
        return TestEntity.class.getSimpleName();
    }
}
