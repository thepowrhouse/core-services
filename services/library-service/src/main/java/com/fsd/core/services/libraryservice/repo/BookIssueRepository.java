package com.fsd.core.services.libraryservice.repo;

import com.fsd.core.services.libraryservice.models.BookIssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 */
public interface BookIssueRepository extends JpaRepository<BookIssueEntity, Integer> {

    BookIssueEntity findByBookEntityIdAndUserEntityId(Integer bookId, Integer userId);

}
