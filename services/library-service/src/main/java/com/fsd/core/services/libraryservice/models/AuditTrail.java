package com.fsd.core.services.libraryservice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String bookName;
    
    private String issuerName;
    
    private String releaserName;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    public String getReleaserName(){
    	return releaserName;
    }
    
    public void setReleaserName(String releaserName){
    	this.releaserName = releaserName;
    }
    
}
