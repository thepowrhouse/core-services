package com.fsd.core.services.libraryservice.models.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fayaz on 29-11-2017.
 */
@Data
public class UserDTO {
    @ApiModelProperty(notes = "id of the user")
    private Integer id;
    @ApiModelProperty(notes = "username")
    private String username;
    @ApiModelProperty(notes = "useremail")
    private String useremail;
    @ApiModelProperty(notes = "password")
    private String password;
    @ApiModelProperty(notes = "role")
    private String role;
    @ApiModelProperty(notes = "user book issue details")
    private List<UserIssuesDTO> userIssuesDTOList = new ArrayList<UserIssuesDTO>();
    @ApiModelProperty(notes = "createdAt")
    private Date createdAt;
    @ApiModelProperty(notes = "updatedAt")
    private Date updatedAt;
}
