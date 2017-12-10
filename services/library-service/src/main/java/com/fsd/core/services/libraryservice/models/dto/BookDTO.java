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
    private String description;
    private String publisher;
    private String year_of_publication;
    private String imageURL;
    private String status;
    private Date createdAt;
    private Date updatedAt;

}
