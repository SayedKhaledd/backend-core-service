package com.example.backendcoreservice.controller;

import com.example.backendcoreservice.dto.TemplateDto;
import com.example.backendcoreservice.service.TemplateService;

public interface TemplateController<E extends TemplateService, T extends TemplateDto> {

    E getService();
}
