package com.example.backendcoreservice.dto;

import java.time.LocalDateTime;

public interface Dto {
    LocalDateTime getCreatedDate();

    LocalDateTime getModifiedDate();

    String getCreatedBy();

    String getModifiedBy();
}
