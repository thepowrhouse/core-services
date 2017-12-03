package com.fsd.core.services.libraryservice.services;

import com.fsd.core.services.libraryservice.models.dto.BookResponseDTO;

public interface LibraryService {
    BookResponseDTO findBookByTitle(String bookName);

    BookResponseDTO save(BookResponseDTO book);
}
