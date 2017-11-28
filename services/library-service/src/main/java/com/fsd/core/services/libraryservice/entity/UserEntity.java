package com.fsd.core.services.libraryservice.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by fayaz on 28-11-2017.
 */
@Entity
@Table(name = "USERS")
public class UserEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", length = 8, unique = true, nullable = false)
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
    private List<BookIssueEntity> currentIssues;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<BookIssueEntity> getCurrentIssues() {
        return currentIssues;
    }

    public void setCurrentIssues(List<BookIssueEntity> currentIssues) {
        this.currentIssues = currentIssues;
    }
}
