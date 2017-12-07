package com.fsd.core.services.libraryservice.controllers;

import com.fsd.core.services.libraryservice.exception.ResourceNotFoundException;
import com.fsd.core.services.libraryservice.models.dto.UserDTO;
import com.fsd.core.services.libraryservice.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiParams;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by fayaz on 29-11-2017.
 */
@RestController
@RequestMapping("/users")
@Api(value="user operations", description="Operations pertaining to user management")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String USER_CONTROLLER = "UserController";

    @Autowired
    UserService userService;

    @ApiOperation(value = "Get a list of all users",response = List.class)
    @GetMapping("")
    public List<UserDTO> getAllUsers() {
        return userService.findAll();
    }

    @ApiOperation(value = "paginated result",response = UserDTO.class)
    @ApiParams(queryparams = {@ApiQueryParam(name = "page", description = "Page", required = true),
            @ApiQueryParam(name = "size", description = "Fetch Size", required = true)})
    @ApiMethod(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Response OK", response = Page.class),
            @ApiResponse(code = 400, message = "Input Exception", response = VndErrors.class),
            @ApiResponse(code = 401, message = "Unauthorized Exception", response = VndErrors.class),
            @ApiResponse(code = 404, message = "Resource Not Found Exception", response = VndErrors.class),
            @ApiResponse(code = 500, message = "Internal Service Exception", response = VndErrors.class)

    })
    @RequestMapping(value = "/paginatedsearch", params = {"page", "size"})
    public Page<UserDTO> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
        return userService.findWithPagination(page, size);
    }

    @ApiOperation(value = "get user by id",response = UserDTO.class)
    @ApiParams(queryparams = {@ApiQueryParam(name = "id", description = "User id", required = true)})
    @ApiMethod(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Response OK with UserDTO as Response", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Input Exception", response = VndErrors.class),
            @ApiResponse(code = 401, message = "Unauthorized Exception", response = VndErrors.class),
            @ApiResponse(code = 404, message = "Resource Not Found Exception", response = VndErrors.class),
            @ApiResponse(code = 500, message = "Internal Service Exception", response = VndErrors.class)

    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Integer userId) {
        UserDTO user = userService.findById(userId);
        if (user == null) {
            throw new ResourceNotFoundException(userId,"User not found");
        }
        return ResponseEntity.ok().body(user);
    }

    @ApiOperation(value = "Create a user",response = UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Response OK with UserDTO as Response", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Input Exception", response = VndErrors.class),
            @ApiResponse(code = 401, message = "Unauthorized Exception", response = VndErrors.class),
            @ApiResponse(code = 404, message = "Resource Not Found Exception", response = VndErrors.class),
            @ApiResponse(code = 500, message = "Internal Service Exception", response = VndErrors.class)

    })
    @PostMapping("/")
    public UserDTO createUser(@RequestBody UserDTO user) {
        return userService.create(user);
    }

    @ApiOperation(value = "Update a user",response = UserDTO.class)
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Response OK with updated UserDTO as Response", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Input Exception", response = VndErrors.class),
            @ApiResponse(code = 401, message = "Unauthorized Exception", response = VndErrors.class),
            @ApiResponse(code = 404, message = "Resource Not Found Exception", response = VndErrors.class),
            @ApiResponse(code = 500, message = "Internal Service Exception", response = VndErrors.class)

    })
    public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id") Integer userId,
                                              @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(userService.update(userDTO));
    }

    @ApiOperation(value = "Delete a user",response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Response OK with deleted UserDTO as Response", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Input Exception", response = VndErrors.class),
            @ApiResponse(code = 401, message = "Unauthorized Exception", response = VndErrors.class),
            @ApiResponse(code = 404, message = "Resource Not Found Exception", response = VndErrors.class),
            @ApiResponse(code = 500, message = "Internal Service Exception", response = VndErrors.class)

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable(value = "id") Integer userId) {
        UserDTO user = userService.findById(userId);
        if (user == null) {
            throw new ResourceNotFoundException(userId,"User not found");
        }
        userService.delete(user);
        return ResponseEntity.ok().build();
    }
}
