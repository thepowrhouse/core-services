package com.fsd.core.services.libraryservice.models.dto;

import lombok.Data;

@Data
public class BookResponseDTO {
    private String title;
    private String description;
    private String author;
    private String imageUrl;
}
