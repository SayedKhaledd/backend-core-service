package com.example.backendcoreservice.transformer.mapper;

import com.example.backendcoreservice.dto.TemplateDto;
import com.example.backendcoreservice.model.TemplateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TemplateMapper<E extends TemplateEntity, T extends TemplateDto> {
    E transformTemplateDtoToTemplateEntity(T dto);

    T transformTemplateEntityToTemplateDto(E entity);
}
