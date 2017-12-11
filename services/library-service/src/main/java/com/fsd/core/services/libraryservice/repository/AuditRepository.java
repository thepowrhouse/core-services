package com.fsd.core.services.libraryservice.repository;

import com.fsd.core.services.libraryservice.models.AuditEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditRepository extends MongoRepository<AuditEntity,String> {
}
