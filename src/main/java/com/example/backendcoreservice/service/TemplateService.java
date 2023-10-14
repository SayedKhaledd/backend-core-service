package com.example.backendcoreservice.service;

import com.example.backendcoreservice.dao.TemplateDao;
import com.example.backendcoreservice.dto.TemplateDto;
import com.example.backendcoreservice.model.TemplateEntity;
import com.example.backendcoreservice.transformer.TemplateTransformer;

public interface TemplateService<E extends TemplateEntity, T extends TemplateDto, K extends TemplateTransformer, L extends TemplateDao> {

    E getTransformer();

    T getDao();
}
