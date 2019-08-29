package com.project.firstAPI.controller;

import com.project.firstAPI.dto.UserDTO;
import com.project.firstAPI.model.Publish;
import com.project.firstAPI.model.User;
import com.project.firstAPI.repository.PublishRepository;
import com.project.firstAPI.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@RequestMapping("/user")
@RestController
@Api(value = "User management system", description = "Operations pertaining to user management system")
public class UserController {
    private static final String PERSON_NOT_FOUND = "User ID not found: %s";

    @Autowired
    UserRepository userRepository;
    @Autowired
    PublishRepository publishRepository;

    //Add User
    @ApiOperation(value = "Add User")
    @PostMapping("")
    public void addUser(@RequestBody final User user, HttpServletRequest request) {
        user.setBrowser(this.getUserAgent(request));
        userRepository.save(user);
    }

    private String getUserAgent(HttpServletRequest request) {
        return request.getHeader("user-agent");
    }

    //Add publish to user
    @ApiOperation(value = "Add publish to specific user")
    @PostMapping("/{userId}/{publishId}")
    public void addPublish(@PathVariable final Integer userId, @PathVariable final Integer publishId) {
        Publish p = publishRepository.findById(publishId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        User u = userRepository.findById(userId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(PERSON_NOT_FOUND, userId)));
        p.setUser(u);
        u.getPublishs().add(p);
        publishRepository.save(p);
        userRepository.save(u);
    }

    //Get all users
    @ApiOperation(value = "Get all users")
    @GetMapping("")
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> convertToDto(user)).collect(Collectors.toList());
    }

    private UserDTO convertToDto(User user) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDTO.class);
    }

    //Get specific user
    @ApiOperation(value = "Get a specific user by id")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(PERSON_NOT_FOUND, id)));
    }

    //Update user
    @ApiOperation(value = "Update data for specific user by id")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(PERSON_NOT_FOUND, id)));
        if (isNull(u)) {
            return new ResponseEntity<>("Unable to update. User with id " + id + " not found.", HttpStatus.NOT_FOUND);
        }
        u.setName(user.getName());
        u.setLastName(user.getLastName());
        u.setBrowser(user.getBrowser());
        userRepository.save(u);
        return new ResponseEntity<User>(u, HttpStatus.ACCEPTED);
    }

    //Delete specific user
    @ApiOperation(value = "Delete a specific user by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Integer id) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(PERSON_NOT_FOUND, id)));
        if (isNull(u)) {
            return new ResponseEntity<>("Unable to update. User with id " + id + " not found.", HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
        return new ResponseEntity<User>(HttpStatus.ACCEPTED);
    }

    //Delete all users
    @ApiOperation(value = "Delete all users")
    @DeleteMapping(value = "")
    public void deleteAllUser() {
        userRepository.deleteAll();
    }

}
