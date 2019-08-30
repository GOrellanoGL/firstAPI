package com.project.firstAPI.controller;

import com.project.firstAPI.repository.CommentRepository;
import com.project.firstAPI.repository.CountCommentByPublishRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CommentControllerTest {

    @Mock
    private CommentRepository commentRepository;
    @Mock
    private CountCommentByPublishRepository commentByPublishRepository;
    @InjectMocks
    private CommentController commentController;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }

    @Test
    public void addCommentTest() throws Throwable {
/*        Publish publish = new Publish();


        Comment comment = new Comment();
        comment.setId(1);
        comment.setDescription("comentario de prueba");
        comment.setDateComment("30-08-2019");
        comment.setOwner("Gonzalo Orellano");*/

    }
}
