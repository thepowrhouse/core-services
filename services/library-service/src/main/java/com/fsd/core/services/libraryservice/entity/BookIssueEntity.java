package com.fsd.core.services.libraryservice.entity;

import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by fayaz on 28-11-2017.
 */
public class BookIssueEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", length = 8, unique = true, nullable = false)
    private Integer id;

    @JoinColumn(name = "BOOK_ID", insertable = false, updatable = false)
    private BookEntity bookEntity;
    @ManyToOne
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private UserEntity userEntity;

    @Column(name = "ISSUED_ON")
    DateTime issuedOn;
    @Column(name = "DUE_DATE")
    DateTime dueDate;
    @Column(name = "RETURNED_DATE")
    DateTime returnedDate;
    @Column(name = "FINE")
    Integer fine;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookEntity getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public DateTime getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(DateTime issuedOn) {
        this.issuedOn = issuedOn;
    }

    public DateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(DateTime dueDate) {
        this.dueDate = dueDate;
    }

    public DateTime getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(DateTime returnedDate) {
        this.returnedDate = returnedDate;
    }

    public Integer getFine() {
        return fine;
    }

    public void setFine(Integer fine) {
        this.fine = fine;
    }
}
