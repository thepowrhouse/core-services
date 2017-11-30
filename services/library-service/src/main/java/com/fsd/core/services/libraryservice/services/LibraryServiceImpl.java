package com.fsd.core.services.libraryservice.services;

import com.fsd.core.services.libraryservice.entity.BookEntity;
import com.fsd.core.services.libraryservice.entity.BookIssueEntity;
import com.fsd.core.services.libraryservice.entity.UserEntity;
import com.fsd.core.services.libraryservice.models.dto.BookResponseDTO;
import com.fsd.core.services.libraryservice.repo.BookIssueRepository;
import com.fsd.core.services.libraryservice.repo.BookRepository;
import com.fsd.core.services.libraryservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class LibraryServiceImpl implements LibraryService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookIssueRepository bookIssueRepository;

    int numberOfDaysToExpire = 15;

    @Override
    public BookResponseDTO findBookByName(String bookName) {
        return null;
    }

    @Override
    @Transactional
    public void issueBook(Integer bookId, Integer userId) {
        UserEntity userEntity = userRepository.findOne(userId);
        BookEntity bookEntity =
                bookRepository.findOne(bookId);
        BookIssueEntity bookIssueEntity = new BookIssueEntity();
        bookIssueEntity.setBookEntity(bookEntity);
        bookIssueEntity.setUserEntity(userEntity);
        bookIssueEntity.setIssuedOn(new Date());
        Date dueDate = new Date(new Date().getTime() + (1000 * 60 * 60 * 24) * numberOfDaysToExpire);
        bookIssueEntity.setDueDate(dueDate);
        bookIssueEntity.setFine(0);
        bookIssueRepository.save(bookIssueEntity);
    }

    @Override
    public void releaseBook(Integer bookId, Integer userId) {
        UserEntity userEntity = userRepository.findOne(userId);
        BookEntity bookEntity =
                bookRepository.findOne(bookId);
        BookIssueEntity bookIssueEntity = bookIssueRepository.findByBookEntityIdAndUserEntityId(bookId, userId);
        bookIssueRepository.delete(bookIssueEntity);
    }
}
