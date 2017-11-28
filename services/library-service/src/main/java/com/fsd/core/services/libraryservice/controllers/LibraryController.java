package com.fsd.core.services.libraryservice.controllers;

import com.fsd.core.services.libraryservice.models.dto.BookResponseDTO;
import com.fsd.core.services.libraryservice.services.LibraryService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiParams;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LibraryController {

    private static final Logger log = LoggerFactory.getLogger(LibraryController.class);

    private static final String LIBRARY_CONTROLLER = "LibraryController";

    @Autowired
    LibraryService libraryService;

    // Get Book
    @ApiParams(queryparams = { @ApiQueryParam(name = "bookName", description = "Book Name", required = true)})
    @ApiMethod(produces = MediaType.APPLICATION_JSON_VALUE)

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Response OK", response = BookResponseDTO.class),
            @ApiResponse(code = 400, message = "Input Exception", response = VndErrors.class),
            @ApiResponse(code = 401, message = "Unauthorized Exception", response = VndErrors.class),
            @ApiResponse(code = 404, message = "Resource Not Found Exception", response = VndErrors.class),
            @ApiResponse(code = 500, message = "Internal Service Exception", response = VndErrors.class)

    })
    //@PreAuthorize("#oauth2.hasScope('Ticketing')")
    @RequestMapping(value = "/books",method = RequestMethod.GET)
    public BookResponseDTO findBookByName(@RequestParam(required = true) String bookName) {

        log.info("Get Book for Name : {} ", bookName);

        return libraryService.findBookByName(bookName);
    }
}
