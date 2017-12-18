package com.fsd.core.services.libraryservice.services;

import com.fsd.core.services.libraryservice.exception.UnprocessableRequestException;
import com.fsd.core.services.libraryservice.models.UserEntity;
import com.fsd.core.services.libraryservice.models.dto.AuditDTO;
import com.fsd.core.services.libraryservice.models.dto.UserDTO;
import com.fsd.core.services.libraryservice.repository.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.fsd.core.services.libraryservice.transformers.UserTransformer.toUserDTO;
import static com.fsd.core.services.libraryservice.transformers.UserTransformer.toUserEntity;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuditMessagePublisher publisher;

    public UserDTO findUserByName(String userName) {
        return toUserDTO(userRepository.findByUsername(userName));
    }

    @HystrixCommand
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(userEntity -> toUserDTO(userEntity)).collect(Collectors.toList());
    }

    public Page<UserDTO> findWithPagination(int page, int size) {
        Page<UserDTO> dtoPage = userRepository.findAll(new PageRequest(page, size)).map(userEntity -> toUserDTO(userEntity));
        if (page > dtoPage.getTotalPages()) {
            throw new UnprocessableRequestException("unable to process users pagination request");
        }
        return dtoPage;
    }

    @HystrixCommand
    public UserDTO findById(Integer id) {
        return toUserDTO(userRepository.findOne(id));
    }

    @Override
    @HystrixCommand
    public UserDTO create(UserDTO userDTO) {
        publisher.sendAuditInfo(new AuditDTO("USER_CREATED"));
        return toUserDTO(userRepository.save(toUserEntity(userDTO)));
    }

    @Override
    @HystrixCommand
    public UserDTO update(UserDTO userDTO) {
        UserEntity userEntity = userRepository.findByUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setUseremail(userDTO.getUseremail());
        userEntity.setRole(userDTO.getRole());
        publisher.sendAuditInfo(new AuditDTO("USER_UPDATED"));
        //Add updatable fields here
        return toUserDTO(userRepository.save(userEntity));
    }

    @Override
    @HystrixCommand
    public UserDTO delete(UserDTO userDTO) {
        userRepository.delete(userDTO.getId());
        publisher.sendAuditInfo(new AuditDTO("USER_DELETED"));
        return userDTO;
    }
}
