package com.example.backendcoreservice.dao;

import com.example.backendcoreservice.model.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateDao<E extends TemplateEntity, T extends JpaRepository<E, Long>> {

    T getRepo();
}
