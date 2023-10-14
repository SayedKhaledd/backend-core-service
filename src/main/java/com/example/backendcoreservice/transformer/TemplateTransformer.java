package com.example.backendcoreservice.transformer;

import com.example.backendcoreservice.dto.TemplateDto;
import com.example.backendcoreservice.model.TemplateEntity;
import com.example.backendcoreservice.transformer.mapper.TemplateMapper;

public interface TemplateTransformer<E extends TemplateEntity, T extends TemplateDto, K extends TemplateMapper<E, T>> {
    K getMapper();

    default E transformTemplateDtoToTemplateEntity(T dto) {
        return (E) getMapper().transformTemplateDtoToTemplateEntity(dto);
    }

    default T transformTemplateEntityToTemplateDto(E entity) {
        return (T) getMapper().transformTemplateEntityToTemplateDto(entity);
    }

}
