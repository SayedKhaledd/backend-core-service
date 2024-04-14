package com.example.backendcoreservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AbstractDto {
    private Long id;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private String createdBy;

    private String modifiedBy;

    private Boolean markedAsDeleted = false;
}
