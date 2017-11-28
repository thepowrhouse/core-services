package com.fsd.core.services.libraryservice.models.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fayaz on 29-11-2017.
 */
public class UserDTO {

    private Integer id;
    private String username;
    private String useremail;
    private String password;
    private String role;
    private List<UserIssuesDTO> userIssuesDTOList = new ArrayList<UserIssuesDTO>();


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

    public List<UserIssuesDTO> getUserIssuesDTOList() {
        return userIssuesDTOList;
    }

    public void setUserIssuesDTOList(List<UserIssuesDTO> userIssuesDTOList) {
        this.userIssuesDTOList = userIssuesDTOList;
    }
}
