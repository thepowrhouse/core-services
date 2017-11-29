package com.fsd.core.services.libraryservice.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String bookId;
    private String bookName;
    private String issuerName;
    private String releaserName;

    public Audit(){

    }

    public Audit(String bookId, String bookName, String issuerName, String releaserName){
        this.bookId = bookId;
        this.bookName = bookName;
        this.issuerName = issuerName;
        this.releaserName = releaserName;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName(){
        return bookName;
    }

    public void setBookName(String bookName){
        this.bookName = bookName;
    }

    public String getIssuerName(){
        return issuerName;
    }

    public void setIssuerName(String issuerName){
        this.issuerName = issuerName;
    }

    public String getReleaserName() {
        return releaserName;
    }

    public void setReleaserName(String releaserName) {
        this.releaserName = releaserName;
    }
}
