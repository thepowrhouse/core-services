package com.fsd.core.services.libraryservice.models.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fayaz on 29-11-2017.
 */
@Data
public class UserDTO {

    private Integer id;
    private String username;
    private String useremail;
    private String password;
    private String role;
    private List<UserIssuesDTO> userIssuesDTOList = new ArrayList<UserIssuesDTO>();
    private Date createdAt;
    private Date updatedAt;
}
