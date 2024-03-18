package com.example.backendcoreservice.transformer.mapper;

import com.example.backendcoreservice.dto.AbstractDto;
import com.example.backendcoreservice.model.AbstractEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AbstractMapper<E extends AbstractEntity, T extends AbstractDto> {
    E transformAbstractDtoToAbstractEntity(T dto);

    T transformAbstractEntityToAbstractDto(E entity);
}
