package com.fsd.core.services.libraryservice.services;

public interface LibraryService {

    public void issueBook(Integer bookId, Integer userId);

    public void releaseBook(Integer bookId, Integer userId);
}
