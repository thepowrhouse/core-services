package com.fsd.core.services.libraryservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "USERS")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class UserEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "userId", length = 8, unique = true, nullable = false)
    private Integer id;

    @Column(name = "USERNAME")
    private String username;

    @Email(message = "Please enter a valid email")
    @Column(name = "USEREMAIL", nullable = false, unique = true)
    @NotEmpty(message = "Email cannot be empty")
    private String useremail;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, max = 15, message = "Your password must between 6 and 15 characters")
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ROLE")
    private String role;

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL)
    private List<BookIssueEntity> currentIssues=new ArrayList<>();

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
}
