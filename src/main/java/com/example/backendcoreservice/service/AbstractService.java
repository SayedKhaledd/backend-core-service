package com.example.backendcoreservice.service;

import com.example.backendcoreservice.dao.AbstractDao;
import com.example.backendcoreservice.dto.AbstractDto;
import com.example.backendcoreservice.model.AbstractEntity;
import com.example.backendcoreservice.transformer.AbstractTransformer;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;

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

    default T create(T dto) {
        log.info("AbstractService: create() was called -  dto{}", dto);
        if (dto.getId() != null && findById(dto.getId()) != null)
            throw new EntityNotFoundException(String.format("Entity with id %s already exists", dto.getId()));
        E entity = (E) getTransformer().transformDtoToEntity(dto);

        log.info("AbstractService: create() was called -  entity{}", entity);
        return (T) getTransformer().transformEntityToDto(getDao().create(entity));
    }
    default T update(T dto) {
        log.info("AbstractService: update() was called -  dto{}", dto);
        if (dto.getId() == null || findById(dto.getId()) == null)
            throw new EntityNotFoundException(String.format("Entity with id %s does not exist", dto.getId()));
        E entity = (E) getTransformer().transformDtoToEntity(dto);
        log.info("AbstractService: update() was called -  entity{}", entity);
        return (T) getTransformer().transformEntityToDto(getDao().update(entity));
    }

}
