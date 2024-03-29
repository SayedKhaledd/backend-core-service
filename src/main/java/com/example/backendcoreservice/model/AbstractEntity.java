package com.example.backendcoreservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@MappedSuperclass
public class AbstractEntity {
    @Column(name = "created_date")
    private LocalDateTime creationDate;

    @Column(name = "modified_date")
    private LocalDateTime modificationDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "marked_as_deleted")
    private Boolean markedAsDeleted = false;

}
