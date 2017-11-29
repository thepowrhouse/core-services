package com.fsd.core.services.libraryservice.services;

import com.fsd.core.services.libraryservice.models.dto.BookResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class LibraryServiceImpl implements LibraryService {
    @Override
    public BookResponseDTO findBookByName(String bookName) {
        return null;
    }
}
