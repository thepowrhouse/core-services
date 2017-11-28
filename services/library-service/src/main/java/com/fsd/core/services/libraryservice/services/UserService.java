package com.fsd.core.services.libraryservice.services;

import com.fsd.core.services.libraryservice.models.dto.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    public UserDTO findUserByName(String userName);

    public UserDTO findById(Integer id);

    public List<UserDTO> findAll();

    public Page<UserDTO> findWithPagination(int page, int size);

    public UserDTO create(UserDTO userDTO);

    public UserDTO update(UserDTO userDTO);

    public UserDTO delete(UserDTO user);
}
