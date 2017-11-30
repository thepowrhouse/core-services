package com.fsd.core.services.libraryservice.models.dto;

/**
 * Created by fayaz on 30-11-2017.
 */
public class ReleaseBookRequest {

    Integer userId;
    Integer bookId;

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
