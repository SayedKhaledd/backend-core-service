package com.example.backendcoreservice.controller;

import com.example.backendcoreservice.api.ApiResponseBuilder;
import com.example.backendcoreservice.dto.AbstractDto;
import com.example.backendcoreservice.service.AbstractService;

public interface AbstractController<E extends AbstractService, T extends AbstractDto> {

    E getService();

    ApiResponseBuilder<T> getApiResponseBuilder();

}
