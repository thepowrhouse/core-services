package com.fsd.core.services.libraryservice.models.dto;

import org.joda.time.DateTime;

/**
 * Created by fayaz on 29-11-2017.
 */
public class UserIssuesDTO {

    private Integer id;
    private Integer bookId;
    private String bookName;
    DateTime issuedOn;
    DateTime dueDate;
    DateTime returnedDate;
    Integer fine;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public DateTime getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(DateTime issuedOn) {
        this.issuedOn = issuedOn;
    }

    public DateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(DateTime dueDate) {
        this.dueDate = dueDate;
    }

    public DateTime getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(DateTime returnedDate) {
        this.returnedDate = returnedDate;
    }

    public Integer getFine() {
        return fine;
    }

    public void setFine(Integer fine) {
        this.fine = fine;
    }
}
