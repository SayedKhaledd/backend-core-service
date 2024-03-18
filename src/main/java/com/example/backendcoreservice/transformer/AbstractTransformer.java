package com.example.backendcoreservice.transformer;

import com.example.backendcoreservice.dto.AbstractDto;
import com.example.backendcoreservice.model.AbstractEntity;
import com.example.backendcoreservice.transformer.mapper.AbstractMapper;

import java.util.List;

public interface AbstractTransformer<E extends AbstractEntity, T extends AbstractDto, K extends AbstractMapper<E, T>> {
    K getMapper();

    default E transformAbstractDtoToAbstractEntity(T dto) {
        return (E) getMapper().transformAbstractDtoToAbstractEntity(dto);
    }

    default List<E> transformAbstractDtosToAbstractEntities(List<T> dtos) {
        return dtos.stream().map(this::transformAbstractDtoToAbstractEntity).toList();
    }

    default T transformAbstractEntityToAbstractDto(E entity) {
        return (T) getMapper().transformAbstractEntityToAbstractDto(entity);
    }

    default List<T> transformAbstractEntitiesToAbstractDtos(List<E> entities) {
        return entities.stream().map(this::transformAbstractEntityToAbstractDto).toList();
    }


}
