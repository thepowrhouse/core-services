package com.fsd.core.services.libraryservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fayaz on 28-11-2017.
 */
@Data
@Entity
@Table(name = "BOOK_ISSUES")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class BookIssueEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", length = 8, unique = true, nullable = false)
    private Integer id;

    @JoinColumn(name = "BOOK_ID", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private BookEntity bookEntity;
    @ManyToOne
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private UserEntity userEntity;

    @Column(name = "ISSUED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    Date issuedOn;
    @Column(name = "DUE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    Date dueDate;
    @Column(name = "RETURNED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    Date returnedDate;
    @Column(name = "FINE")
    Integer fine;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
}
