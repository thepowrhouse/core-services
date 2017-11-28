package com.fsd.core.services.libraryservice.entity;

import javax.persistence.*;

/**
 * Created by fayaz on 28-11-2017.
 */
@Entity
@Table(name = "BOOKS")
public class BookEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", length = 8, unique = true, nullable = false)
    private Integer id;

    @Column(name = "ISBN", nullable = false, unique = true)
    private String isbn;

    @Column(name = "AUTHOR", nullable = false)
    private String author;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "CALLNUMBER", length = 10)
    private String callnumber;

    @Column(name = "PUBLISHER", nullable = false)
    private String publisher;

    @Column(name = "YEAR_OF_PUBLICATION")
    private String year_of_publication;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCallnumber() {
        return callnumber;
    }

    public void setCallnumber(String callnumber) {
        this.callnumber = callnumber;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getYear_of_publication() {
        return year_of_publication;
    }

    public void setYear_of_publication(String year_of_publication) {
        this.year_of_publication = year_of_publication;
    }
}
