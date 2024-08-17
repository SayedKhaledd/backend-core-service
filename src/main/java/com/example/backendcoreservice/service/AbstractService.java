package com.example.backendcoreservice.service;

import com.example.backendcoreservice.dao.AbstractDao;
import com.example.backendcoreservice.dto.AbstractDto;
import com.example.backendcoreservice.model.AbstractEntity;
import com.example.backendcoreservice.transformer.AbstractTransformer;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;

public interface AbstractService<E extends AbstractEntity, T extends AbstractDto, K extends AbstractTransformer, L extends AbstractDao> {
    Logger log = org.slf4j.LoggerFactory.getLogger(AbstractService.class);

    K getTransformer();

    L getDao();

    default T findById(Long id) {
        Optional<E> dto = getDao().findById(id);
        if (dto.isPresent()) {
            return (T) getTransformer().transformEntityToDto(dto.get());
        }
        throw new EntityNotFoundException("Entity not found");
    }

    default List<T> findAll() {
        return getTransformer().transformEntitiesToDtos(getDao().findAll());
    }

    default T create(T dto) {
        log.info("AbstractService: create() was called -  dto{}", dto);
        E entity = (E) getTransformer().transformDtoToEntity(dto);
        return (T) getTransformer().transformEntityToDto(getDao().create(entity));
    }

    default T update(T dto, Long id) {
        log.info("AbstractService: update() was called -  dto{}", dto);
        if (id == null || findById(id) == null)
            throw new EntityNotFoundException(String.format("Entity with id %s does not exist", id));
        E entity = (E) getTransformer().transformDtoToEntity(dto);
        return (T) getTransformer().transformEntityToDto(getDao().update(entity));
    }

}
