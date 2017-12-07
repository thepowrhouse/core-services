package com.fsd.core.services.libraryservice.repo;

import com.fsd.core.services.libraryservice.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    public UserEntity findByUsername(String username);

}
