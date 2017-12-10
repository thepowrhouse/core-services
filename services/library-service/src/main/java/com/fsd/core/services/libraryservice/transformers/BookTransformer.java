package com.fsd.core.services.libraryservice.transformers;

import com.fsd.core.services.libraryservice.exception.ResourceNotFoundException;
import com.fsd.core.services.libraryservice.models.BookEntity;
import com.fsd.core.services.libraryservice.models.dto.BookDTO;

public class BookTransformer {

    public static BookEntity toBookEntity(BookDTO bookDTO) {

        if (bookDTO == null) {
            throw new ResourceNotFoundException(1,"No Books Found");
        }

        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn(bookDTO.getIsbn());
        bookEntity.setAuthor(bookDTO.getAuthor());
        bookEntity.setTitle(bookDTO.getTitle());
        bookEntity.setDescription(bookDTO.getDescription());
        bookEntity.setPublisher(bookDTO.getPublisher());
        bookEntity.setYear_of_publication(bookDTO.getYear_of_publication());
        bookEntity.setImageURL(bookDTO.getImageURL());
        bookEntity.setStatus(bookDTO.getStatus());
        bookEntity.setCreatedAt(new java.util.Date());
        bookEntity.setUpdatedAt(new java.util.Date());
        return bookEntity;
    }

    public static BookDTO toBookDTO(BookEntity bookEntity) {

        if (bookEntity == null) {
            throw new ResourceNotFoundException(1,"No Books Found");
        }

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(bookEntity.getId());
        bookDTO.setIsbn(bookEntity.getIsbn());
        bookDTO.setAuthor(bookEntity.getAuthor());
        bookDTO.setTitle(bookEntity.getTitle());
        bookDTO.setDescription(bookEntity.getDescription());
        bookDTO.setPublisher(bookEntity.getPublisher());
        bookDTO.setYear_of_publication(bookEntity.getYear_of_publication());
        bookDTO.setImageURL(bookEntity.getImageURL());
        bookDTO.setStatus(bookEntity.getStatus());
        bookDTO.setCreatedAt(bookEntity.getCreatedAt());
        bookDTO.setUpdatedAt(bookEntity.getUpdatedAt());
        return bookDTO;
    }
}
