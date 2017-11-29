package com.fsd.core.services.libraryservice.services;

import com.fsd.core.services.libraryservice.models.Book;
import com.fsd.core.services.libraryservice.models.dto.BookResponseDTO;
import com.fsd.core.services.libraryservice.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LibraryServiceImpl implements LibraryService {

    @Resource
    private BookRepository repository;

    @Override
    public BookResponseDTO findBookByTitle(String bookName) {
        Book book = repository.findByTitle(bookName);
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        if (null != book) {
            BeanUtils.copyProperties(book, bookResponseDTO);
            return bookResponseDTO;
        }
        throw new RuntimeException("Book not fount:" + bookName);
    }

    @Override
    public BookResponseDTO save(BookResponseDTO bookResponseDTO) {
        Book book = new Book();
        BeanUtils.copyProperties(bookResponseDTO, book);
        BookResponseDTO responseDTO = new BookResponseDTO();
        BeanUtils.copyProperties(repository.save(book), responseDTO);
        return responseDTO;
    }
}
