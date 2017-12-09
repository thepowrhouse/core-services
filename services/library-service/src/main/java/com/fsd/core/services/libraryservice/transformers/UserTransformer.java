package com.fsd.core.services.libraryservice.transformers;

import com.fsd.core.services.libraryservice.exception.ResourceNotFoundException;
import com.fsd.core.services.libraryservice.exception.UnprocessableRequestException;
import com.fsd.core.services.libraryservice.models.UserEntity;
import com.fsd.core.services.libraryservice.models.dto.UserDTO;

import java.util.stream.Collectors;

public class UserTransformer {

    public static UserDTO toUserDTO(UserEntity userEntity) {

        if (userEntity == null) {
            throw new ResourceNotFoundException(1,"No Users Found");
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setUseremail(userEntity.getUseremail());
        userDTO.setRole(userEntity.getRole());
        userDTO.setUserIssuesDTOList(userEntity.getCurrentIssues().stream().map(UserIssuesTransformer::toUserIssuesDTO).collect(Collectors.toList()));
        userDTO.setCreatedAt(userEntity.getCreatedAt());
        userDTO.setUpdatedAt(userEntity.getUpdatedAt());
        return userDTO;
    }

    public static UserEntity toUserEntity(UserDTO userDTO) {

        if (userDTO == null) {
            throw new ResourceNotFoundException(1,"No Users Found");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setUseremail(userDTO.getUseremail());
        userEntity.setRole(userDTO.getRole());
        userEntity.setCreatedAt(new java.util.Date());
        userEntity.setUpdatedAt(new java.util.Date());
        return userEntity;
    }


}
