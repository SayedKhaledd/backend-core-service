package com.example.backendcoreservice.dao;

import com.example.backendcoreservice.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbstractDao<E extends AbstractEntity, T extends JpaRepository<E, Long>> {

    T getRepo();
}
