package com.fsd.core.services.libraryservice.models.dto;

/**
 * Created by fayaz on 29-11-2017.
 */
public class BookDTO {

    private Integer id;
    private String isbn;
    private String author;
    private String title;
    private String callnumber;
    private String publisher;
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
