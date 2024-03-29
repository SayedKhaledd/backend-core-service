package com.example.backendcoreservice.service;

import com.example.backendcoreservice.dao.AbstractDao;
import com.example.backendcoreservice.dto.AbstractDto;
import com.example.backendcoreservice.model.AbstractEntity;
import com.example.backendcoreservice.transformer.AbstractTransformer;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public interface AbstractService<E extends AbstractEntity, T extends AbstractDto, K extends AbstractTransformer, L extends AbstractDao> {

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
        if (dto.getId() != null && findById(dto.getId()) != null)
            throw new EntityNotFoundException(String.format("Entity with id %s already exists", dto.getId()));

        E entity = (E) getTransformer().transformDtoToEntity(dto);
        return (T) getTransformer().transformEntityToDto((E) getDao().getRepo().save(entity));
    }
}
