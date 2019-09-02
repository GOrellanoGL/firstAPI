package com.project.firstAPI.controller;

import com.project.firstAPI.model.Comment;
import com.project.firstAPI.model.CommentByPublish;
import com.project.firstAPI.repository.CommentRepository;
import com.project.firstAPI.repository.CountCommentByPublishRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

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
    public void addCommentTest() throws Exception {
        Comment comment = new Comment(1, "Comentario de prueba", "30-08-2019", "Gonzalo Orellano", null);
        when(commentRepository.save(comment)).thenReturn(comment);
        commentController.addComment(comment);
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    public void getAllComment() throws Exception {
        List<Comment> commentList = Arrays.asList(
                new Comment(1, "descripcion 1", "30-08-2019", "Gonzalo Orellano", null),
                new Comment(2, "descripcion 2", "30-08-2019", "Simon Orellano", null)
        );
        when(commentRepository.findAll()).thenReturn(commentList);
        commentController.getAllComment();
        verify(commentRepository, times(1)).findAll();
    }

    @Test
    public void getCommentById() throws Exception {
        Integer id = 1;
        Comment comment = new Comment(1, "descripcion 1", "30-08-2019", "Gonzalo Orellano", null);
        when(commentRepository.findById(id)).thenReturn(java.util.Optional.of(comment));
        commentController.deleteCommentById(1);
        verify(commentRepository, times(1)).findById(id);
    }

    @Test
    public void deleteAllComment() throws Exception {
        commentController.deleteAllComment();
        verify(commentRepository, times(1)).deleteAll();
    }

    @Test
    public void deleteCommentById() throws Exception {
        Integer id = 1;
        Comment comment = new Comment(1, "descripcion 1", "30-08-2019", "Gonzalo Orellano", null);
        when(commentRepository.findById(id)).thenReturn(java.util.Optional.of(comment));
        commentController.deleteCommentById(id);
        verify(commentRepository, times(1)).deleteById(id);
    }

    @Test
    public void getCommentByPublishList() throws Exception {
        List<CommentByPublish> commentByPublishList = Arrays.asList(
                new CommentByPublish(1, "Titulo", "Gonzalo Orellano", 10),
                new CommentByPublish(2, "Titulo 1", "Simon Orellano", 20)
        );
        when(commentByPublishRepository.getCount()).thenReturn(commentByPublishList);
        commentController.getCommentByPublishList();
        verify(commentByPublishRepository, times(1)).getCount();
    }

    @Test
    public void listCommentByPublishPageByPage() throws Exception {
        Integer page = 1;
        Integer size = 10;
        List<CommentByPublish> commentByPublishList = Arrays.asList(
                new CommentByPublish(1, "Titulo", "Gonzalo Orellano", 10),
                new CommentByPublish(2, "Titulo 1", "Simon Orellano", 20)
        );
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<CommentByPublish> pageComment = new PageImpl<CommentByPublish>(commentByPublishList);
        when(commentByPublishRepository.getCountWithPagination(pageRequest)).thenReturn(pageComment);
        commentController.listCommentByPublishPageByPage(page, size);
        verify(commentByPublishRepository, times(1)).getCountWithPagination(pageRequest);
    }
}
