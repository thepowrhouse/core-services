package com.fsd.core.services.libraryservice.repo;

import com.fsd.core.services.libraryservice.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 */
public interface BookRepository extends JpaRepository<BookEntity, Integer> {


}
