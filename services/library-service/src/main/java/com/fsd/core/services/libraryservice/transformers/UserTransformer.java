package com.fsd.core.services.libraryservice.transformers;

import com.fsd.core.services.libraryservice.models.UserEntity;
import com.fsd.core.services.libraryservice.models.dto.UserDTO;

import java.util.stream.Collectors;

/**
 * Created by fayaz on 29-11-2017.
 */
public class UserTransformer {

    public static UserDTO toUserDTO(UserEntity userEntity) {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setUseremail(userEntity.getUseremail());
        userDTO.setRole(userEntity.getRole());
        userDTO.setUserIssuesDTOList(userEntity.getCurrentIssues().stream().map(bookIssueEntity -> {
            return UserIssuesTransformer.toUserIssuesDTO(bookIssueEntity);
        }).collect(Collectors.toList()));
        return userDTO;
    }

    public static UserEntity toUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setUseremail(userDTO.getUseremail());
        userEntity.setRole(userDTO.getRole());
        return userEntity;
    }


}
