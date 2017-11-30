package com.fsd.core.services.libraryservice.models.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by fayaz on 29-11-2017.
 */
@Data
public class UserIssuesDTO {

    private Integer id;
    private Integer bookId;
    private String bookName;
    Date issuedOn;
    Date dueDate;
    Date returnedDate;
    Integer fine;
    private Date createdAt;
    private Date updatedAt;
}
