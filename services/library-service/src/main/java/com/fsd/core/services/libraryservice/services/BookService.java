package com.fsd.core.services.libraryservice.services;

import com.fsd.core.services.libraryservice.models.dto.BookDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {

    public BookDTO findByTitle(String bookName);

    public BookDTO findById(Integer id);

    public List<BookDTO> findAll();

    public Page<BookDTO> findWithPagination(int page, int size);

    public BookDTO create(BookDTO bookDTO);

    public BookDTO update(BookDTO bookDTO);

    public BookDTO delete(BookDTO book);
}
