package com.fsd.core.services.libraryservice.transformers;

import com.fsd.core.services.libraryservice.models.BookIssueEntity;
import com.fsd.core.services.libraryservice.models.dto.UserIssuesDTO;

public class UserIssuesTransformer {

    public static UserIssuesDTO toUserIssuesDTO(BookIssueEntity bookIssueEntity) {
        UserIssuesDTO userIssuesDTO = new UserIssuesDTO();
        userIssuesDTO.setId(bookIssueEntity.getId());
        userIssuesDTO.setBookId(bookIssueEntity.getBookEntity().getId());
        userIssuesDTO.setBookName(bookIssueEntity.getBookEntity().getTitle());
        userIssuesDTO.setDueDate(bookIssueEntity.getDueDate());
        userIssuesDTO.setFine(bookIssueEntity.getFine());
        userIssuesDTO.setIssuedOn(bookIssueEntity.getIssuedOn());
        userIssuesDTO.setReturnedDate(bookIssueEntity.getReturnedDate());
        userIssuesDTO.setCreatedAt(bookIssueEntity.getCreatedAt());
        userIssuesDTO.setUpdatedAt(bookIssueEntity.getUpdatedAt());
        return userIssuesDTO;
    }
}
