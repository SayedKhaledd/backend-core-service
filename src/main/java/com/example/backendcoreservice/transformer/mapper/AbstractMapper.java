package com.example.backendcoreservice.transformer.mapper;

import com.example.backendcoreservice.dto.AbstractDto;
import com.example.backendcoreservice.dto.Dto;
import com.example.backendcoreservice.model.AbstractEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AbstractMapper<E extends AbstractEntity, T extends Dto> {

    E transformDtoToEntity(T dto);

    T transformEntityToDto(E entity);
}
