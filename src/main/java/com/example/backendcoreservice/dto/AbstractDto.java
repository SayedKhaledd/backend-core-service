package com.example.backendcoreservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractDto {
    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private Boolean markedAsDeleted = false;
}
