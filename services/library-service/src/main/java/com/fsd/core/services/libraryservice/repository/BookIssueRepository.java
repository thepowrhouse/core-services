package com.fsd.core.services.libraryservice.repository;

import com.fsd.core.services.libraryservice.models.BookIssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 */
public interface BookIssueRepository extends JpaRepository<BookIssueEntity, Integer> {

    List<BookIssueEntity> findByBookEntityIdAndUserEntityId(Integer bookId, Integer userId);

}
