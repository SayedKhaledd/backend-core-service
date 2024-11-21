package com.example.backendcoreservice.transformer;

import com.example.backendcoreservice.dto.AbstractDto;
import com.example.backendcoreservice.dto.Dto;
import com.example.backendcoreservice.model.AbstractEntity;
import com.example.backendcoreservice.transformer.mapper.AbstractMapper;

import java.util.List;

public interface AbstractTransformer<E extends AbstractEntity, T extends Dto, K extends AbstractMapper<E, T>> {
    K getMapper();

    default E transformDtoToEntity(T dto) {
        return getMapper().transformDtoToEntity(dto);
    }

    default List<E> transformDtosToEntities(List<T> dtos) {
        return dtos.stream().map(this::transformDtoToEntity).toList();
    }

    default T transformEntityToDto(E entity) {
        return (T) getMapper().transformEntityToDto(entity);
    }

    default List<T> transformEntitiesToDtos(List<E> entities) {
        return entities.stream().map(this::transformEntityToDto).toList();
    }


}
