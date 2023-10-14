package com.example.backendcoreservice.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateDto {
    private Long id;

    private LocalDateTime creationDate;

    private LocalDateTime modificationDate;

    private String createdBy;

    private String modifiedBy;

    private Boolean markedAsDeleted = false;
}
