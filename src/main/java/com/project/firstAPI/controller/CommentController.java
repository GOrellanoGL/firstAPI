package com.project.firstAPI.controller;

import com.project.firstAPI.model.Comment;
import com.project.firstAPI.model.CommentByPublish;
import com.project.firstAPI.repository.CommentRepository;
import com.project.firstAPI.repository.CountCommentByPublishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RequestMapping("/comment")
@RestController
public class CommentController {
    private static final String COMMENT_NOT_FOUND = "Comment ID not found: %s";

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CountCommentByPublishRepository commentByPublishRepository;

    //Add comment
    @PostMapping("")
    public void addComment(@RequestBody final Comment comment) {
        commentRepository.save(comment);
    }

    //Get all comments
    @GetMapping("")
    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    //Get specific comment
    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable final Integer id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(COMMENT_NOT_FOUND, id)));
    }

    //Delete all comments
    @DeleteMapping(value = "")
    public void deleteAllComment() {
        commentRepository.deleteAll();
    }

    //Delete specific comment
    public void deleteCommentById(@PathVariable final Integer id) {
        commentRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(COMMENT_NOT_FOUND, id)));
    }

    //Get count of comments by publish
    @GetMapping(value = "")
    public List<CommentByPublish> getCommentByPublishList() {
        return commentByPublishRepository.getCount();
    }

}
