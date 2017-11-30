package com.fsd.core.services.libraryservice.models.dto;

import lombok.Data;

/**
 * Created by fayaz on 30-11-2017.
 */
@Data
public class ReleaseBookResponse {

    Integer userId;
    Integer bookId;

    public ReleaseBookResponse(Integer userId, Integer bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }
}
