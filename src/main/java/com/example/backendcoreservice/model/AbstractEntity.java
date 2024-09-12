package com.example.backendcoreservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@SoftDelete(columnName = "marked_as_deleted", strategy = SoftDeleteType.DELETED )
@NoArgsConstructor
@AllArgsConstructor
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity {
    @Column(name = "created_date")
    @CreatedDate
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;

    //TODO: MAY NEED TO DELETE THIS FIELD AND USE SOFT DELETE ANNOTATION
    @Column(name = "marked_as_deleted",insertable = false, updatable = false)
    private Boolean markedAsDeleted = false;

}
