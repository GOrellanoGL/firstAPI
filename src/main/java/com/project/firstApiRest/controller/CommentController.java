package com.project.firstApiRest.controller;

import com.project.firstApiRest.model.Comment;
import com.project.firstApiRest.model.CommentByPublish;
import com.project.firstApiRest.repository.CommentRepository;
import com.project.firstApiRest.repository.CountCommentByPublishRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static java.util.Objects.isNull;

/**Comment controller.**/
@RequestMapping("/comment")
@RestController
@Api(value = "Comment management system",
        description = "Operations pertaining to comment management system")
public class CommentController {

    /**
     * COMMENT_NOT_FOUND.
     **/
    private static final String COMMENT_NOT_FOUND = "Comment ID not found: %s";
    /**
     * CommentRepository.
     **/
    @Autowired
    private CommentRepository commentRepository;
    /**
     * CountCommentByPublishRepository.
     **/
    @Autowired
    private CountCommentByPublishRepository commentByPublishRepository;

    /**
     * Add comment.
     *
     * @param comment .
     */
    @ApiOperation(value = "Add comment")
    @PostMapping("")
    public void addComment(@RequestBody final Comment comment) {
        commentRepository.save(comment);
    }

    /**
     * Get all comments.
     *
     * @return A list with comments.
     */
    @ApiOperation(value = "Get all comments")
    @GetMapping("")
    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    /**
     * Get specific comment.
     *
     * @param id .
     * @return A comment by id.
     */
    @ApiOperation(value = "Get specific comment by id")
    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable final Integer id) {
        return commentRepository
                .findById(id)
                .orElseThrow(() -> new HttpClientErrorException(
                        HttpStatus.BAD_REQUEST,
                        String.format(COMMENT_NOT_FOUND, id)));
    }

    /**
     * Delete all comments.
     **/
    @ApiOperation(value = "Delete all comments")
    @DeleteMapping(value = "")
    public void deleteAllComment() {
        commentRepository.deleteAll();
    }

    /**
     * Delete specific comment.
     *
     * @param id .
     * @return A response entity for delete comment by id.
     */
    @ApiOperation(value = "Delete specific comment by id")
    public ResponseEntity<?> deleteCommentById(@PathVariable final Integer id) {
        Comment c = commentRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(
                        HttpStatus.BAD_REQUEST,
                        String.format(COMMENT_NOT_FOUND, id)));
        if (isNull(c)) {
            return new ResponseEntity<>("Unable to delete."
                    + " Comment with id " + id + " not found.",
                    HttpStatus.NOT_FOUND);
        }
        commentRepository.deleteById(id);
        return new ResponseEntity<Comment>(HttpStatus.ACCEPTED);
    }

    /**
     * Get count of comments by publish.
     *
     * @return A list wit comment by publish.
     */
    @ApiOperation(value = "Get count of comments by publish")
    @GetMapping(value = "/countCommentByPublish")
    public List<CommentByPublish> getCommentByPublishList() {
        return commentByPublishRepository.getCount();
    }

    /**
     * Get count of comments by publish with pagination.
     *
     * @param page .
     * @param size .
     * @return A page with comment by publish.
     */
    @ApiOperation(value = "Get count of comments by publish with pagination")
    @GetMapping(value = "/countCommentByPublish/pagination")
    public Page<CommentByPublish> listCommentByPublishPageByPage(
            @RequestParam(value = "page", defaultValue = "1",
                    required = false) final Integer page,
            @RequestParam(value = "size", defaultValue = "30",
                    required = false) final Integer size) {
        return commentByPublishRepository.getCountWithPagination(
                PageRequest.of(page - 1, size));
    }

}
