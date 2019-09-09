package com.project.apirest.controller;

import com.project.apirest.model.Publish;
import com.project.apirest.model.User;
import com.project.apirest.repository.PublishRepository;
import com.project.apirest.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class UserControllerTest {

    private final static HttpClientErrorException HTTP_PUBLISH_ERROR_EXCEPTION = new HttpClientErrorException(
            HttpStatus.BAD_REQUEST);
    private final static HttpClientErrorException HTTP_CLIENT_ERROR_EXCEPTION = new HttpClientErrorException(
            HttpStatus.BAD_REQUEST,
            String.format("PERSON_NOT_FOUND", 10));
    private final static User USER = new User(1, "Gonzalo", "Orellano", "Chrome", null);
    private final static List<User> USER_LIST = Arrays.asList(
            new User(1, "Gonzalo", "Orellano", "Chrome", null),
            new User(2, "Simon", "Orellano", "Firefox", null)
    );
    private final static Publish PUBLISH = new Publish(1, "Titulo 1", "Descripcion 1", LocalDateTime.now(), "", 10, USER, null);
    private final static Integer ID = 1;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PublishRepository publishRepository;
    @InjectMocks
    private UserController userController;
    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getAllUsers() throws Exception {
        //GIVEN
        when(userRepository.findAll()).thenReturn(USER_LIST);
        //WHEN
        userController.getAllUser();
        //THEN
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void getUserById() throws Exception {
        //GIVEN
        when(userRepository.findById(ID)).thenReturn(java.util.Optional.of(USER));
        //WHEN
        userController.getUserById(ID);
        //THEN
        verify(userRepository, times(1)).findById(ID);
    }

    @Test(expected = HttpClientErrorException.class)
    public void getUserByIdError() {
        when(userRepository.findById(ID)).thenThrow(HTTP_CLIENT_ERROR_EXCEPTION);
        userController.getUserById(ID);
    }

    @Test
    public void deleteUserById() throws Exception {
        when(userRepository.findById(ID)).thenReturn(java.util.Optional.of(USER));
        userController.deleteUserById(ID);
        verify(userRepository, times(1)).deleteById(ID);
    }

    @Test(expected = HttpClientErrorException.class)
    public void deleteUserByIdError() {
        when(userRepository.findById(ID)).thenThrow(HTTP_CLIENT_ERROR_EXCEPTION);
        userController.deleteUserById(ID);
    }

    @Test
    public void deleteAllUsers() throws Exception {
        userController.deleteAllUser();
        verify(userRepository, times(1)).deleteAll();
    }

    @Test
    public void addUser() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("user-agent", "Google Chrome");
        USER.setBrowser(this.getUserAgent(request));
        when(userRepository.save(USER)).thenReturn(USER);
        userController.addUser(USER, request);
        verify(userRepository, times(1)).save(USER);
    }

    private String getUserAgent(HttpServletRequest request) {
        return request.getHeader("user-agent");
    }

    @Test
    public void addPublish() throws Exception {
/*        Publish publish = new Publish(1, "Title", "Description", LocalDateTime.now(), "", 10, null,null);
        List<Publish> publishList = Arrays.asList(publish);
        User user = new User(1, "Gonzalo", "Orellano", "", null);
        publish.setUser(user);
        user.getPublishs().add(publish);
        when(publishRepository.findById(1)).thenReturn(java.util.Optional.of(publish));
        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        userController.addPublish(1, 1);
        verify(publishRepository, times(1)).save(publish);
        verify(userRepository, times(1)).save(user);*/
    }

    @Test(expected = HttpClientErrorException.class)
    public void addPublishError() {
        when(publishRepository.findById(ID)).thenThrow(HTTP_PUBLISH_ERROR_EXCEPTION);
        userController.addPublish(ID, ID);
    }

    @Test(expected = HttpClientErrorException.class)
    public void addPublishUserError() {
        when(publishRepository.findById(ID)).thenReturn(java.util.Optional.of(PUBLISH));
        when(userRepository.findById(ID)).thenThrow(HTTP_CLIENT_ERROR_EXCEPTION);
        userController.addPublish(ID, ID);
    }

    @Test
    public void updateUser() throws Exception {
        when(userRepository.findById(ID)).thenReturn(java.util.Optional.of(USER));
        userController.updateUser(ID, USER);
        verify(userRepository, times(1)).save(USER);
    }

    @Test(expected = HttpClientErrorException.class)
    public void updateUserError() {
        when(userRepository.findById(ID)).thenThrow(HTTP_CLIENT_ERROR_EXCEPTION);
        userController.updateUser(ID, USER);
    }

}
