package com.fsd.core.services.libraryservice.services;

import com.fsd.core.services.libraryservice.models.BookEntity;
import com.fsd.core.services.libraryservice.models.BookIssueEntity;
import com.fsd.core.services.libraryservice.models.UserEntity;
import com.fsd.core.services.libraryservice.models.dto.AuditDTO;
import com.fsd.core.services.libraryservice.repository.BookIssueRepository;
import com.fsd.core.services.libraryservice.repository.BookRepository;
import com.fsd.core.services.libraryservice.repository.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;

@Component
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookIssueRepository bookIssueRepository;

    int numberOfDaysToExpire = 15;

    @Autowired
    private AuditMessagePublisher publisher;

    @Override
    @Transactional
    @HystrixCommand
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
        bookIssueEntity.setCreatedAt(new Date());
        bookIssueEntity.setUpdatedAt(new Date());
        bookIssueRepository.save(bookIssueEntity);
        publisher.sendAuditInfo(new AuditDTO("BOOK_ISSUED"));
    }

    @Override
    @HystrixCommand
    public void releaseBook(Integer bookId, Integer userId) {
        BookIssueEntity bookIssueEntity = bookIssueRepository.findByBookEntityIdAndUserEntityId(bookId, userId);
        bookIssueRepository.delete(bookIssueEntity);
        publisher.sendAuditInfo(new AuditDTO("BOOK_RELEASED"));
    }
}
