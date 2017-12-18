package com.fsd.core.services.libraryservice.models.dto;

import com.fsd.core.services.libraryservice.models.UserEntity;
import lombok.Data;

import java.util.Date;

@Data
public class AuditDTO {
    private Integer id;
    private UserEntity userEntity;
    private String event;
    private Date createdAt;
    private Date updatedAt;

    public AuditDTO(String event) {
        this.createdAt = new Date();
        this.event = event;
        this.updatedAt = new Date();
    }
}
