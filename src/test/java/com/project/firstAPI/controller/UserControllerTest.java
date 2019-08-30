package com.project.firstAPI.controller;

import com.project.firstAPI.model.Publish;
import com.project.firstAPI.model.User;
import com.project.firstAPI.repository.PublishRepository;
import com.project.firstAPI.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class UserControllerTest {

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
        List<User> users = Arrays.asList(
                new User(1, "Gonzalo", "Orellano", "Chrome", null),
                new User(2, "Simon", "Orellano", "Firefox", null)
        );
        when(userRepository.findAll()).thenReturn(users);
        //WHEN
        userController.getAllUser();
        //THEN
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void getUserById() throws Exception {
        //GIVEN
        Integer id = 1;
        User user = new User(1, "Gonzalo", "Orellano", "Chrome", null);
        when(userRepository.findById(id)).thenReturn(java.util.Optional.of(user));
        //WHEN
        userController.getUserById(id);
        //THEN
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    public void deleteUserById() throws Exception {
        Integer id = 1;
        User user = new User(1, "Gonzalo", "Orellano", "Chrome", null);
        when(userRepository.findById(id)).thenReturn(java.util.Optional.of(user));
        userController.deleteUserById(id);
        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    public void deleteAllUsers() throws Exception {
        userController.deleteAllUser();
        verify(userRepository, times(1)).deleteAll();
    }

    @Test
    public void addUser() throws Exception {
        User user = new User(1, "Gonzalo", "Orellano", "", null);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("user-agent", "Google Chrome");
        user.setBrowser(this.getUserAgent(request));
        when(userRepository.save(user)).thenReturn(user);
        userController.addUser(user, request);
        verify(userRepository, times(1)).save(user);
    }

    private String getUserAgent(HttpServletRequest request) {
        return request.getHeader("user-agent");
    }

    @Test
    public void addPublish() throws Exception {
        Publish publish = new Publish(1, "Title", "Description", LocalDateTime.now(), "", 10, null,null);
        List<Publish> publishList = Arrays.asList(publish);
        User user = new User(1, "Gonzalo", "Orellano", "", null);
        publish.setUser(user);
        user.getPublishs().add(publish);
        when(publishRepository.findById(1)).thenReturn(java.util.Optional.of(publish));
        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        userController.addPublish(1, 1);
        verify(publishRepository, times(1)).save(publish);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void updateUser() throws Exception {
        User user = new User(1, "Gonzalo", "Orellano", "Chrome", null);
        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        userController.updateUser(1, user);
        verify(userRepository, times(1)).save(user);
    }

}
