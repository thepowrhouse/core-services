package com.fsd.core.services.libraryservice.repository;

import com.fsd.core.services.libraryservice.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findByTitle(String title);
}
