package com.fsd.core.services.libraryservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.fsd.core.services.libraryservice.models.AuditTrail;

public interface BookRepositoryService extends MongoRepository<AuditTrail, String> {
    public AuditTrail findByBookId(String bookId);
}
