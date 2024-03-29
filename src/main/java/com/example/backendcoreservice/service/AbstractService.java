package com.example.backendcoreservice.service;

import com.example.backendcoreservice.dao.AbstractDao;
import com.example.backendcoreservice.dto.AbstractDto;
import com.example.backendcoreservice.model.AbstractEntity;
import com.example.backendcoreservice.transformer.AbstractTransformer;

public interface AbstractService<E extends AbstractEntity, T extends AbstractDto, K extends AbstractTransformer, L extends AbstractDao> {

    K getTransformer();

    L getDao();
}
