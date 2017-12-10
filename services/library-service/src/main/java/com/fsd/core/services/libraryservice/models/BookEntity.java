package com.fsd.core.services.libraryservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "BOOKS")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class BookEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "BOOK_ID", length = 8, unique = true, nullable = false)
    private Integer id;

    @Column(name = "ISBN", nullable = false, unique = true)
    private String isbn;

    @Column(name = "AUTHOR", nullable = false)
    private String author;

    @Column(name = "TITLE", nullable = false, unique = true)
    private String title;

    @Column(name = "DESCRIPTION", length = 10)
    private String description;

    @Column(name = "PUBLISHER", nullable = false)
    private String publisher;

    @Column(name = "IMAGEURL", nullable = false)
    private String imageURL;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "YEAR_OF_PUBLICATION")
    private String year_of_publication;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

}
