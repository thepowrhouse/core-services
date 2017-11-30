package com.fsd.core.services.libraryservice.services;

import com.fsd.core.services.libraryservice.models.dto.BookResponseDTO;

public interface LibraryService {
    public BookResponseDTO findBookByName(String bookName);

    public void issueBook(Integer bookId, Integer userId);

    public void releaseBook(Integer bookId, Integer userId);
}
