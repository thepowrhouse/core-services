package com.fsd.core.services.libraryservice.services;

import com.fsd.core.services.libraryservice.models.dto.BookResponseDTO;

public interface LibraryService {
    public BookResponseDTO findBookByName(String bookName);
}
