package com.fsd.core.services.libraryservice.repository;

import com.fsd.core.services.libraryservice.models.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 */
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    public BookEntity findByTitle(String title);
}
