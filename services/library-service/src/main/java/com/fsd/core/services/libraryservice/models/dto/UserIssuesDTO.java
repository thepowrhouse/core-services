package com.fsd.core.services.libraryservice.models.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by fayaz on 29-11-2017.
 */
@Data
public class UserIssuesDTO {

    @ApiModelProperty(notes = "book issue no")
    private Integer id;
    @ApiModelProperty(notes = "bookId")
    private Integer bookId;
    @ApiModelProperty(notes = "name of the book")
    private String bookName;
    @ApiModelProperty(notes = "issued On")
    Date issuedOn;
    @ApiModelProperty(notes = "due Date")
    Date dueDate;
    @ApiModelProperty(notes = "returned Date")
    Date returnedDate;
    @ApiModelProperty(notes = "fine")
    Integer fine;
    @ApiModelProperty(notes = "created At")
    private Date createdAt;
    @ApiModelProperty(notes = "updated At")
    private Date updatedAt;
}
