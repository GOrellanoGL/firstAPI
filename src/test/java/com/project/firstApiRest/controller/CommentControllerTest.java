package com.project.firstApiRest.controller;

import com.project.firstApiRest.model.Comment;
import com.project.firstApiRest.model.CommentByPublish;
import com.project.firstApiRest.repository.CommentRepository;
import com.project.firstApiRest.repository.CountCommentByPublishRepository;
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

    private final static List<Comment> COMMENT_LIST = Arrays.asList(
            new Comment(1, "descripcion 1", "30-08-2019", "Gonzalo Orellano", null),
            new Comment(2, "descripcion 2", "30-08-2019", "Simon Orellano", null)
    );
    private final static List<CommentByPublish> COMMENT_BY_PUBLISH_LIST = Arrays.asList(
            new CommentByPublish(1, "Titulo", "Gonzalo Orellano", 10),
            new CommentByPublish(2, "Titulo 1", "Simon Orellano", 20)
    );
    private final static Comment COMMENT = new Comment(1, "Comentario de prueba", "30-08-2019", "Gonzalo Orellano", null);
    private final static Integer ID = 1;
    private final static Integer PAGE = 1;
    private final static Integer SIZE = 10;
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
        when(commentRepository.save(COMMENT)).thenReturn(COMMENT);
        commentController.addComment(COMMENT);
        verify(commentRepository, times(1)).save(COMMENT);
    }

    @Test
    public void getAllComment() throws Exception {
        when(commentRepository.findAll()).thenReturn(COMMENT_LIST);
        commentController.getAllComment();
        verify(commentRepository, times(1)).findAll();
    }

    @Test
    public void getCommentById() throws Exception {
        when(commentRepository.findById(ID)).thenReturn(java.util.Optional.of(COMMENT));
        commentController.deleteCommentById(ID);
        verify(commentRepository, times(1)).findById(ID);
    }

    @Test
    public void deleteAllComment() throws Exception {
        commentController.deleteAllComment();
        verify(commentRepository, times(1)).deleteAll();
    }

    @Test
    public void deleteCommentById() throws Exception {
        when(commentRepository.findById(ID)).thenReturn(java.util.Optional.of(COMMENT));
        commentController.deleteCommentById(ID);
        verify(commentRepository, times(1)).deleteById(ID);
    }

    @Test
    public void getCommentByPublishList() throws Exception {
        when(commentByPublishRepository.getCount()).thenReturn(COMMENT_BY_PUBLISH_LIST);
        commentController.getCommentByPublishList();
        verify(commentByPublishRepository, times(1)).getCount();
    }

    @Test
    public void listCommentByPublishPageByPage() throws Exception {
        List<CommentByPublish> commentByPublishList = Arrays.asList(
                new CommentByPublish(1, "Titulo", "Gonzalo Orellano", 10),
                new CommentByPublish(2, "Titulo 1", "Simon Orellano", 20)
        );
        PageRequest pageRequest = PageRequest.of(PAGE - 1, SIZE);
        Page<CommentByPublish> pageComment = new PageImpl<CommentByPublish>(commentByPublishList);
        when(commentByPublishRepository.getCountWithPagination(pageRequest)).thenReturn(pageComment);
        commentController.listCommentByPublishPageByPage(PAGE, SIZE);
        verify(commentByPublishRepository, times(1)).getCountWithPagination(pageRequest);
    }
}
