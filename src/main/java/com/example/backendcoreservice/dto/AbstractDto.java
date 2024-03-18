package com.example.backendcoreservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AbstractDto {
    private Long id;

    private LocalDateTime creationDate;

    private LocalDateTime modificationDate;

    private String createdBy;

    private String modifiedBy;

    private Boolean markedAsDeleted = false;
}
