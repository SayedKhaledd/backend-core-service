package com.example.backendcoreservice.util;

import com.example.backendcoreservice.model.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "test_entity")
@Entity
public class TestEntity extends AbstractEntity {
    @Id
    private Long id;
}
