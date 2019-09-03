/**
 * @since 1.0
 * @author Gonzalo Orellano
 * @version 1.0
 */
package com.project.apirest.controller;

import com.project.apirest.dto.UserDTO;
import com.project.apirest.model.Publish;
import com.project.apirest.model.User;
import com.project.apirest.repository.PublishRepository;
import com.project.apirest.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**User controller.**/
@RequestMapping("/user")
@RestController
@Api(value = "User management system")
public class UserController {
    /**Person not found.**/
    private static final String PERSON_NOT_FOUND = "User ID not found: %s";
    /**User repository.**/
    @Autowired
    private UserRepository userRepository;
    /**Publish repository.**/
    @Autowired
    private PublishRepository publishRepository;

    /** Add user.
     * @param user .
     * @param request .
     */
    @ApiOperation(value = "Add User")
    @PostMapping("")
    public void addUser(@RequestBody final User user,
                        final HttpServletRequest request) {
        user.setBrowser(this.getUserAgent(request));
        userRepository.save(user);
    }

    /**Get user agent.
     * @param request .
     * @return user agent.
     */
    private String getUserAgent(final HttpServletRequest request) {
        return request.getHeader("user-agent");
    }

    /** Add publish to user.
     * @param userId user id.
     * @param publishId publish id.
     */
    @ApiOperation(value = "Add publish to specific user")
    @PostMapping("/{userId}/{publishId}")
    public void addPublish(@PathVariable final Integer userId,
                           @PathVariable final Integer publishId) {
        Publish publish = publishRepository.findById(publishId)
                .orElseThrow(() -> new HttpClientErrorException(
                        HttpStatus.BAD_REQUEST));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new HttpClientErrorException(
                        HttpStatus.BAD_REQUEST,
                        String.format(PERSON_NOT_FOUND, userId)));
        publish.setUser(user);
        user.getPublishes().add(publish);
        publishRepository.save(publish);
        userRepository.save(user);
    }

    /** Get all users.
     * @return A list with users.
     */
    @ApiOperation(value = "Get all users")
    @GetMapping("")
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(
                user -> convertToDto(user)).collect(Collectors.toList());
    }

    /**Convert to DTO.
     * @param user User.
     * @return UserDTO.
     */
    private UserDTO convertToDto(final User user) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDTO.class);
    }

    /** Get a specific user by id.
     * @param id .
     * @return A specific user by id.
     */
    @ApiOperation(value = "Get a specific user by id")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") final Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(
                        HttpStatus.BAD_REQUEST,
                        String.format(PERSON_NOT_FOUND, id)));
    }

    /** Update data for specific user by id.
     * @param id id.
     * @param user user.
     * @return response entity.
     */
    @ApiOperation(value = "Update data for specific user by id")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") final Integer id,
                                        @RequestBody final User user) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(
                        HttpStatus.BAD_REQUEST,
                        String.format(PERSON_NOT_FOUND, id)));
        if (isNull(u)) {
            return new ResponseEntity<>("Unable to update. User with id "
                    + id + " not found.", HttpStatus.NOT_FOUND);
        }
        u.setName(user.getName());
        u.setLastName(user.getLastName());
        u.setBrowser(user.getBrowser());
        userRepository.save(u);
        return new ResponseEntity<User>(u, HttpStatus.ACCEPTED);
    }

    /** Delete a specific user by id.
     * @param id id.
     * @return response entity.
     */
    @ApiOperation(value = "Delete a specific user by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserById(@PathVariable("id")
                                                final Integer id) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(
                        HttpStatus.BAD_REQUEST,
                        String.format(PERSON_NOT_FOUND, id)));
        if (isNull(u)) {
            return new ResponseEntity<>("Unable to delete. User with id "
                    + id + " not found.", HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
        return new ResponseEntity<User>(HttpStatus.ACCEPTED);
    }

    /** Delete all users.
     */
    @ApiOperation(value = "Delete all users")
    @DeleteMapping(value = "")
    public void deleteAllUser() {
        userRepository.deleteAll();
    }

}
