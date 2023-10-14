package com.example.backendcoreservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "template-entity")
public class TemplateEntity {
    @Id
    @SequenceGenerator(name = "template_entity_sequence", sequenceName = "template_entity_sequence", allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "template_entity_sequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "modification_date")
    private LocalDateTime modificationDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "marked_as_deleted")
    private Boolean markedAsDeleted = false;

}
