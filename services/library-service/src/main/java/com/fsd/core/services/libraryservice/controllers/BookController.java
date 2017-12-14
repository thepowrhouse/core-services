package com.fsd.core.services.libraryservice.controllers;

import com.fsd.core.services.libraryservice.exception.ResourceNotFoundException;
import com.fsd.core.services.libraryservice.models.dto.BookDTO;
import com.fsd.core.services.libraryservice.models.dto.UserDTO;
import com.fsd.core.services.libraryservice.services.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiParams;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Api(value="book operations", description="Operations pertaining to books management")
public class BookController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String BOOK_CONTROLLER = "BookController";

    @Autowired
    BookService bookService;

    @ApiOperation(value = "Get a list of all books",response = List.class)
    @GetMapping("")
    @PreAuthorize("#oauth2.hasScope('LIBRARIAN') or #oauth2.hasScope('PATRON')")
    public List<BookDTO> getAllBooks() {
        return bookService.findAll();
    }

    @ApiOperation(value = "paginated result",response = UserDTO.class)
    @ApiParams(queryparams = {@ApiQueryParam(name = "page", description = "Page", required = true),
            @ApiQueryParam(name = "size", description = "Fetch Size", required = true)})
    @ApiMethod(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Response OK", response = Page.class),
            @ApiResponse(code = 400, message = "Input Exception", response = VndErrors.class),
            @ApiResponse(code = 401, message = "Unauthorized Exception", response = VndErrors.class),
            @ApiResponse(code = 404, message = "Resource Not Found Exception", response = VndErrors.class),
            @ApiResponse(code = 500, message = "Internal Service Exception", response = VndErrors.class)

    })
    @RequestMapping(value = "/paginatedsearch", params = {"page", "size"})
    @PreAuthorize("#oauth2.hasScope('LIBRARIAN') or #oauth2.hasScope('PATRON')")
    public Page<BookDTO> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
        return bookService.findWithPagination(page, size);
    }

    @ApiOperation(value = "get book By id",response = UserDTO.class)
    @ApiParams(queryparams = {@ApiQueryParam(name = "id", description = "book id", required = true)})
    @ApiMethod(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Response OK with UserDTO as Response", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Input Exception", response = VndErrors.class),
            @ApiResponse(code = 401, message = "Unauthorized Exception", response = VndErrors.class),
            @ApiResponse(code = 404, message = "Resource Not Found Exception", response = VndErrors.class),
            @ApiResponse(code = 500, message = "Internal Service Exception", response = VndErrors.class)

    })
    @GetMapping("/{id}")
    @PreAuthorize("#oauth2.hasScope('LIBRARIAN') or #oauth2.hasScope('PATRON')")
    public ResponseEntity<BookDTO> getBookById(@PathVariable(value = "id") Integer bookId) {
        BookDTO book = bookService.findById(bookId);
        if (book == null) {
            throw new ResourceNotFoundException(bookId,"Book not found");
        }
        return ResponseEntity.ok().body(book);
    }

    @ApiOperation(value = "get book By title",response = UserDTO.class)
    @ApiParams(queryparams = {@ApiQueryParam(name = "title", description = "book title", required = true)})
    @ApiMethod(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Response OK with UserDTO as Response", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Input Exception", response = VndErrors.class),
            @ApiResponse(code = 401, message = "Unauthorized Exception", response = VndErrors.class),
            @ApiResponse(code = 404, message = "Resource Not Found Exception", response = VndErrors.class),
            @ApiResponse(code = 500, message = "Internal Service Exception", response = VndErrors.class)

    })
    @GetMapping("/title/{id}")
    @PreAuthorize("#oauth2.hasScope('LIBRARIAN') or #oauth2.hasScope('PATRON')")
    public ResponseEntity<BookDTO> getBookByTitle(@PathVariable(value = "title") String title) {
        BookDTO book = bookService.findByTitle(title);
        if (book == null) {
            throw new RuntimeException(String.format("Book by title '%s' not found",title));
        }
        return ResponseEntity.ok().body(book);
    }

    @ApiOperation(value = "Add a new book",response = UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Response OK with BookDTO as Response", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Input Exception", response = VndErrors.class),
            @ApiResponse(code = 401, message = "Unauthorized Exception", response = VndErrors.class),
            @ApiResponse(code = 404, message = "Resource Not Found Exception", response = VndErrors.class),
            @ApiResponse(code = 500, message = "Internal Service Exception", response = VndErrors.class)

    })
    @PostMapping("/")
    @PreAuthorize("#oauth2.hasScope('LIBRARIAN')")
    public BookDTO createBook(@RequestBody BookDTO book) {
        return bookService.create(book);
    }


    @ApiOperation(value = "Update a book",response = UserDTO.class)
    @PutMapping("/{id}")
    @PreAuthorize("#oauth2.hasScope('LIBRARIAN')")
    public ResponseEntity<BookDTO> updateBook(@PathVariable(value = "id") Integer bookId,
                                              @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok().body(bookService.update(bookDTO));
    }


    @ApiOperation(value = "Delete a book",response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Response OK with deleted BookDTO as Response", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Input Exception", response = VndErrors.class),
            @ApiResponse(code = 401, message = "Unauthorized Exception", response = VndErrors.class),
            @ApiResponse(code = 404, message = "Resource Not Found Exception", response = VndErrors.class),
            @ApiResponse(code = 500, message = "Internal Service Exception", response = VndErrors.class)

    })
    @DeleteMapping("/{id}")
    @PreAuthorize("#oauth2.hasScope('LIBRARIAN')")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable(value = "id") Integer bookId) {
        BookDTO book = bookService.findById(bookId);
        if (book == null) {
            throw new ResourceNotFoundException(bookId,"Book not found");
        }
        bookService.delete(book);
        return ResponseEntity.ok().build();
    }
}
