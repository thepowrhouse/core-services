package com.fsd.core.services.libraryservice.services;

import com.fsd.core.services.libraryservice.entity.BookEntity;
import com.fsd.core.services.libraryservice.exception.MyResourceNotFoundException;
import com.fsd.core.services.libraryservice.models.dto.BookDTO;
import com.fsd.core.services.libraryservice.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.fsd.core.services.libraryservice.transformers.BookTransformer.toBookDTO;
import static com.fsd.core.services.libraryservice.transformers.BookTransformer.toBookEntity;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    public BookDTO findByTitle(String bookName) {
        return toBookDTO(bookRepository.findByTitle(bookName));
    }

    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream().map(bookEntity -> {
            return toBookDTO(bookEntity);
        }).collect(Collectors.toList());
    }

    public Page<BookDTO> findWithPagination(int page, int size) {
        Page<BookDTO> dtoPage = bookRepository.findAll(new PageRequest(page, size)).map(new Converter<BookEntity, BookDTO>() {
            @Override
            public BookDTO convert(BookEntity bookEntity) {
                return toBookDTO(bookEntity);
            }
        });
        if (page > dtoPage.getTotalPages()) {
            throw new MyResourceNotFoundException();
        }
        return dtoPage;
    }

    public BookDTO findById(Integer id) {
        return toBookDTO(bookRepository.findOne(id));
    }

    @Override
    public BookDTO create(BookDTO bookDTO) {
        return toBookDTO(bookRepository.save(toBookEntity(bookDTO)));
    }

    @Override
    public BookDTO update(BookDTO bookDTO) {
        BookEntity bookEntity = bookRepository.findByTitle(bookDTO.getTitle());
        bookEntity.setIsbn(bookDTO.getIsbn());
        bookEntity.setAuthor(bookDTO.getAuthor());
        bookEntity.setTitle(bookDTO.getTitle());
        bookEntity.setCallnumber(bookDTO.getCallnumber());
        bookEntity.setPublisher(bookDTO.getPublisher());
        bookEntity.setYear_of_publication(bookDTO.getYear_of_publication());
        //Add updatable fields here
        return toBookDTO(bookRepository.save(bookEntity));
    }

    @Override
    public BookDTO delete(BookDTO bookDTO) {
        bookRepository.delete(bookDTO.getId());
        return bookDTO;
    }
}
