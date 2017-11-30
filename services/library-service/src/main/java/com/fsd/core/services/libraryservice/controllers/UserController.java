package com.fsd.core.services.libraryservice.controllers;

import com.fsd.core.services.libraryservice.models.dto.UserDTO;
import com.fsd.core.services.libraryservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by fayaz on 29-11-2017.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String USER_CONTROLLER = "UserController";

    @Autowired
    UserService userService;

    @GetMapping("")
    public List<UserDTO> getAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = "/paginatedsearch", params = {"page", "size"})
    public Page<UserDTO> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
        return userService.findWithPagination(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Integer userId) {
        UserDTO user = userService.findById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/")
    public UserDTO createUser(@RequestBody UserDTO user) {
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id") Integer userId,
                                              @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(userService.update(userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable(value = "id") Integer userId) {
        UserDTO user = userService.findById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userService.delete(user);
        return ResponseEntity.ok().build();
    }
}
