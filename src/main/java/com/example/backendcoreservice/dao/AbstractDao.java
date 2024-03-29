package com.example.backendcoreservice.dao;

import com.example.backendcoreservice.model.AbstractEntity;
import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AbstractDao<E extends AbstractEntity, T extends JpaRepository<E, Long>> {

    Logger log = org.slf4j.LoggerFactory.getLogger(AbstractDao.class);

    T getRepo();

    default Optional<E> findById(Long id) {
        return getRepo().findById(id);
    }

    default E create(E entity) {
        log.info("Creating entity {}", entity);
        return getRepo().save(entity);
    }
}
