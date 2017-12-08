package com.fsd.core.services.libraryservice.models.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by fayaz on 29-11-2017.
 */
@Data
public class BookDTO {

    private Integer id;
    private String isbn;
    private String author;
    private String title;
    private String callnumber;
    private String publisher;
    private String year_of_publication;
    private Date createdAt;
    private Date updatedAt;

}
