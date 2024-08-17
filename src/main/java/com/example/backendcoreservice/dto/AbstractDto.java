package com.example.backendcoreservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractDto {
    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private Boolean markedAsDeleted = false;

    private String createdBy;

    private String modifiedBy;
}
