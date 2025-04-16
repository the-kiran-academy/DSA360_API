package com.dsa360.api.entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@javax.persistence.MappedSuperclass
@javax.persistence.EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @javax.persistence.Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @CreatedBy
    @javax.persistence.Column(updatable = false)
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;
}
