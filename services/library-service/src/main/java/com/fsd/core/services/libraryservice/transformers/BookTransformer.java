package com.fsd.core.services.libraryservice.transformers;

import com.fsd.core.services.libraryservice.models.BookEntity;
import com.fsd.core.services.libraryservice.models.dto.BookDTO;

/**
 * Created by fayaz on 29-11-2017.
 */
public class BookTransformer {

    public static BookEntity toBookEntity(BookDTO bookDTO) {

        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn(bookDTO.getIsbn());
        bookEntity.setAuthor(bookDTO.getAuthor());
        bookEntity.setTitle(bookDTO.getTitle());
        bookEntity.setCallnumber(bookDTO.getCallnumber());
        bookEntity.setPublisher(bookDTO.getPublisher());
        bookEntity.setYear_of_publication(bookDTO.getYear_of_publication());
        bookEntity.setCreatedAt(new java.util.Date());
        bookEntity.setUpdatedAt(new java.util.Date());
        return bookEntity;
    }

    public static BookDTO toBookDTO(BookEntity bookEntity) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(bookEntity.getId());
        bookDTO.setIsbn(bookEntity.getIsbn());
        bookDTO.setAuthor(bookEntity.getAuthor());
        bookDTO.setTitle(bookEntity.getTitle());
        bookDTO.setCallnumber(bookEntity.getCallnumber());
        bookDTO.setPublisher(bookEntity.getPublisher());
        bookDTO.setYear_of_publication(bookEntity.getYear_of_publication());
        bookDTO.setCreatedAt(bookEntity.getCreatedAt());
        bookDTO.setUpdatedAt(bookEntity.getUpdatedAt());
        return bookDTO;
    }
}
