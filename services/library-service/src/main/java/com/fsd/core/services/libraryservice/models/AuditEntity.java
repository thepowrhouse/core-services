package com.fsd.core.services.libraryservice.models;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Document(collection = "AUDITS")
@EntityListeners(AuditingEntityListener.class)
public class AuditEntity {
    @Id
    private String _id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity userEntity;

    @Column(name = "EVENT", nullable = false, unique = true)
    private String event;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public AuditEntity() {

    }

    public AuditEntity(String event, Date createdAt, Date updatedAt) {
        this.createdAt = createdAt;
        this.event = event;
        this.updatedAt = updatedAt;
    }

    public AuditEntity(String event) {
        this.createdAt = new Date();
        this.event = event;
        this.updatedAt = new Date();
    }
}