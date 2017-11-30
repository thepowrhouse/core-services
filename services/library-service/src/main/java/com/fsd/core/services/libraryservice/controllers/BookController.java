package com.fsd.core.services.libraryservice.controllers;

import com.fsd.core.services.libraryservice.models.dto.BookDTO;
import com.fsd.core.services.libraryservice.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by fayaz on 29-11-2017.
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String BOOK_CONTROLLER = "BookController";

    @Autowired
    BookService bookService;

    @GetMapping("")
    public List<BookDTO> getAllBooks() {
        return bookService.findAll();
    }

    @RequestMapping(value = "/paginatedsearch", params = {"page", "size"})
    public Page<BookDTO> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
        return bookService.findWithPagination(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable(value = "id") Integer bookId) {
        BookDTO book = bookService.findById(bookId);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(book);
    }

    @PostMapping("/")
    public BookDTO createBook(@RequestBody BookDTO book) {
        return bookService.create(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable(value = "id") Integer bookId,
                                              @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok().body(bookService.update(bookDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable(value = "id") Integer bookId) {
        BookDTO book = bookService.findById(bookId);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        bookService.delete(book);
        return ResponseEntity.ok().build();
    }
}
