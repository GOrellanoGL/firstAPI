package com.project.apirest.controller;

import com.project.apirest.model.Publish;
import com.project.apirest.model.User;
import com.project.apirest.repository.CommentRepository;
import com.project.apirest.repository.PublishRepository;
import com.project.apirest.service.PublishService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class PublishControllerTest {

    private final static HttpClientErrorException HTTP_CLIENT_ERROR_EXCEPTION = new HttpClientErrorException(
            HttpStatus.BAD_REQUEST,
            String.format("COMMENT_NOT_FOUND", 10));
    private final static User USER = new User(1, "Gonzalo", "Orellano", "", null);
    private final static Publish PUBLISH = new Publish(1, "Titulo 1", "Descripcion 1", LocalDateTime.now(), "", 10, USER, null);
    private final static List<Publish> PUBLISH_LIST = Arrays.asList(
            new Publish(1, "Titulo 1", "Descripcion 1", LocalDateTime.now(), "", 10, USER, null),
            new Publish(2, "Titulo 2", "Descripcion 2", LocalDateTime.now(), "", 10, null, null)
    );
    private final static Integer ID = 1;
    @InjectMocks
    PublishController publishController;
    @Mock
    PublishRepository publishRepository;
    @Mock
    CommentRepository commentRepository;
    @Mock
    PublishService publishService;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(publishController).build();
    }

    @Test
    public void addPublish() throws Exception {
        when(publishRepository.save(PUBLISH)).thenReturn(PUBLISH);
        publishController.addPublish(PUBLISH);
        verify(publishRepository, times(1)).save(PUBLISH);
    }

    @Test
    public void addCommentToPublish() throws Exception {
/*        Comment comment = new Comment(1, "Comentario de prueba", "30-08-2019", "Gonzalo Orellano", null);
        List<Comment> commentList = Arrays.asList(comment);
        User user = new User(1, "Gonzalo", "Orellano", "", null);
        Publish publish = new Publish(1, "Titulo 1", "Descripcion 1", LocalDateTime.now(), "", 10, user, null);
        comment.setPublish(publish);
        publish.getComments().add(comment);
        when(commentRepository.findById(1)).thenReturn(java.util.Optional.of(comment));
        when(publishRepository.findById(1)).thenReturn(java.util.Optional.of(publish));
        when(commentRepository.save(comment)).thenReturn(comment);
        when(publishRepository.save(publish)).thenReturn(publish);
        publishController.addCommentToPublish(1,1);
        verify(commentRepository, times(1)).findById(1);
        verify(publishRepository, times(1)).findById(1);
        verify(commentRepository, times(1)).save(comment);
        verify(publishRepository, times(1)).save(publish);*/
    }

    @Test(expected = HttpClientErrorException.class)
    public void addCommentToPublishError() {
        when(commentRepository.findById(ID)).thenThrow(HTTP_CLIENT_ERROR_EXCEPTION);
        publishController.addCommentToPublish(ID, ID);
    }

    @Test
    public void getAllPublish() throws Exception {
        when(publishRepository.findAll()).thenReturn(PUBLISH_LIST);
        publishController.getAllPublish();
        verify(publishRepository, times(1)).findAll();
    }

    @Test
    public void getById() {
        when(publishRepository.findById(ID)).thenReturn(java.util.Optional.of(PUBLISH));
        publishController.getById(ID);
        verify(publishRepository, times(1)).findById(ID);
    }

    @Test(expected = HttpClientErrorException.class)
    public void getByIdError() {
        when(publishRepository.findById(ID)).thenThrow(HTTP_CLIENT_ERROR_EXCEPTION);
        publishController.getById(ID);
    }

    @Test
    public void deleteComment() throws Exception {
        commentRepository.deleteComment();
        verify(commentRepository, times(1)).deleteComment();
    }

/*    @Test
    public void getAsync() {
        ResponseEntity r = new ResponseEntity(HttpStatus.OK);
        List<PublishDTO> publishDTOS = Arrays.asList(
                new PublishDTO(1, "Titulo 1", "Descripcion 1", 10),
                new PublishDTO(2, "Titulo 2", "Descripcion 2", 20)
        );
        when(publishService.getPublish()).thenReturn(CompletableFuture.completedFuture(publishDTOS));
        publishController.getAsync();
        verify(publishService, times(1)).getPublish();
    }*/
}
