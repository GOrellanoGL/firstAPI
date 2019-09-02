package com.project.firstAPI.controller;

import com.project.firstAPI.model.Comment;
import com.project.firstAPI.model.CommentByPublish;
import com.project.firstAPI.repository.CommentRepository;
import com.project.firstAPI.repository.CountCommentByPublishRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static java.util.Objects.isNull;

@RequestMapping("/comment")
@RestController
@Api(value = "Comment management system", description = "Operations pertaining to comment management system")
public class CommentController {
    private static final String COMMENT_NOT_FOUND = "Comment ID not found: %s";

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CountCommentByPublishRepository commentByPublishRepository;

    //Add comment
    @ApiOperation(value = "Add comment")
    @PostMapping("")
    public void addComment(@RequestBody final Comment comment) {
        commentRepository.save(comment);
    }

    //Get all comments
    @ApiOperation(value = "Get all comments")
    @GetMapping("")
    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    //Get specific comment
    @ApiOperation(value = "Get specific comment by id")
    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable final Integer id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(COMMENT_NOT_FOUND, id)));
    }

    //Delete all comments
    @ApiOperation(value = "Delete all comments")
    @DeleteMapping(value = "")
    public void deleteAllComment() {
        commentRepository.deleteAll();
    }

    //Delete specific comment
    @ApiOperation(value = "Delete specific comment by id")
    public ResponseEntity<?> deleteCommentById(@PathVariable final Integer id) {
        Comment c = commentRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(COMMENT_NOT_FOUND, id)));
        if (isNull(c)) {
            return new ResponseEntity<>("Unable to delete. Comment with id " + id + " not found.", HttpStatus.NOT_FOUND);
        }
        commentRepository.deleteById(id);
        return new ResponseEntity<Comment>(HttpStatus.ACCEPTED);
    }

    //Get count of comments by publish
    @ApiOperation(value = "Get count of comments by publish")
    @GetMapping(value = "/countCommentByPublish")
    public List<CommentByPublish> getCommentByPublishList() {
        return commentByPublishRepository.getCount();
    }

    //Get count of comments by publish with pagination
    @ApiOperation(value = "Get count of comments by publish with pagination")
    @GetMapping(value = "/countCommentByPublish/pagination")
    public Page<CommentByPublish> listCommentByPublishPageByPage(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                                                                 @RequestParam(value = "size", defaultValue = "30", required = false) Integer size) {
        return commentByPublishRepository.getCountWithPagination(PageRequest.of(page - 1, size));
    }

}
