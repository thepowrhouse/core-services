package com.fsd.core.services.libraryservice.models.dto;

/**
 * Created by fayaz on 30-11-2017.
 */
public class IssueBookResponse {

    Integer userId;
    Integer bookId;

    public IssueBookResponse() {
    }

    public IssueBookResponse(Integer userId, Integer bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
