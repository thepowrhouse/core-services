package com.fsd.core.services.libraryservice.models.dto;

import lombok.Data;

@Data
public class BookResponseDTO {
    private String bookName;
    private String description;
    private String author;
}
