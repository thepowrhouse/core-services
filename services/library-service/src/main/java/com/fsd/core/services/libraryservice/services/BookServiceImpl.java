package com.fsd.core.services.libraryservice.services;

import com.fsd.core.services.libraryservice.exception.UnprocessableRequestException;
import com.fsd.core.services.libraryservice.models.BookEntity;
import com.fsd.core.services.libraryservice.models.dto.AuditDTO;
import com.fsd.core.services.libraryservice.models.dto.BookDTO;
import com.fsd.core.services.libraryservice.repository.BookRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.fsd.core.services.libraryservice.transformers.BookTransformer.toBookDTO;
import static com.fsd.core.services.libraryservice.transformers.BookTransformer.toBookEntity;

@Service
public class BookServiceImpl implements BookService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BookRepository bookRepository;
    @Autowired
    private AuditMessagePublisher publisher;

    public BookDTO findByTitle(String bookName) {
        return toBookDTO(bookRepository.findByTitle(bookName));
    }

    @HystrixCommand
    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream().map(bookEntity -> toBookDTO(bookEntity)).collect(Collectors.toList());
    }

    public Page<BookDTO> findWithPagination(int page, int size) {
        Page<BookDTO> dtoPage = bookRepository.findAll(new PageRequest(page, size)).map(bookEntity -> toBookDTO(bookEntity));
        if (page > dtoPage.getTotalPages()) {
            throw new UnprocessableRequestException("unable to process books pagination request");
        }
        return dtoPage;
    }

    @Cacheable("books")
    public BookDTO findById(Integer id) {
        logger.info("Reading from DB");
        return toBookDTO(bookRepository.findOne(id));
    }

    @Override
    @HystrixCommand
    public BookDTO create(BookDTO bookDTO) {
        publisher.sendAuditInfo(new AuditDTO("BOOK_ADDED"));
        return toBookDTO(bookRepository.save(toBookEntity(bookDTO)));
    }

    @Override
    @HystrixCommand
    public BookDTO update(BookDTO bookDTO) {
        BookEntity bookEntity = bookRepository.findOne(bookDTO.getId());
        bookEntity.setIsbn(bookDTO.getIsbn());
        bookEntity.setAuthor(bookDTO.getAuthor());
        bookEntity.setTitle(bookDTO.getTitle());
        bookEntity.setDescription(bookDTO.getDescription());
        bookEntity.setPublisher(bookDTO.getPublisher());
        bookEntity.setYear_of_publication(bookDTO.getYear_of_publication());
        bookEntity.setImageURL(bookDTO.getImageURL());
        bookEntity.setStatus(bookDTO.getStatus());
        bookEntity.setUpdatedAt(new java.util.Date());
        //Add updatable fields here
        publisher.sendAuditInfo(new AuditDTO("BOOK_UPDATED"));
        return toBookDTO(bookRepository.save(bookEntity));
    }

    @Override
    @HystrixCommand
    public BookDTO delete(BookDTO bookDTO) {
        bookRepository.delete(bookDTO.getId());
        publisher.sendAuditInfo(new AuditDTO("BOOK_DELETED"));
        return bookDTO;
    }
}
