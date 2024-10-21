package com.example.backendcoreservice.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TestDaoImpl implements TestDao {
    private final TestRepo testRepo;


    @Override
    public TestRepo getRepo() {
        return testRepo;
    }
}
