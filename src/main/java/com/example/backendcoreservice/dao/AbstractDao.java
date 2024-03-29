package com.example.backendcoreservice.dao;

import com.example.backendcoreservice.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AbstractDao<E extends AbstractEntity, T extends JpaRepository<E, Long>> {

    T getRepo();

    default Optional<E> findById(Long id) {
        return getRepo().findById(id);
    }
}
