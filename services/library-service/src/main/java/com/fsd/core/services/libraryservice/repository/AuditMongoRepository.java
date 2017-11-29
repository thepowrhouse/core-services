package com.fsd.core.services.libraryservice.repository;

import com.fsd.core.services.libraryservice.models.Audit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditMongoRepository extends MongoRepository<Audit,String> {

    public Audit findByBookId(String bookId);

}
