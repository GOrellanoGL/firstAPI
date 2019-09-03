package com.project.firstAPI.controller;

import com.project.firstAPI.model.Comment;
import com.project.firstAPI.model.Publish;
import com.project.firstAPI.repository.CommentRepository;
import com.project.firstAPI.repository.PublishRepository;
import com.project.firstAPI.service.PublishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

/**Publish controller.**/
@RequestMapping("/publish")
@RestController
@EnableScheduling
@Api(value = "Publish management system", description =
        "Operations pertaining to publish management system")
public class PublishController {
    /**Fixed rate.**/
    private static final int FIXED_RATE = 1000;
    /**Publish not found.**/
    private static final String PUBLISH_NOT_FOUND = "Publish ID not found: %s";
    /**Publish repository.**/
    @Autowired
    private PublishRepository publishRepository;
    /**Comment repository.**/
    @Autowired
    private CommentRepository commentRepository;
    /**Publish service.**/
    @Autowired
    private PublishService publishService;

    /** Sets the employee’s last name.
     * @param p Publish.
     */
    @ApiOperation(value = "Add publish")
    @PostMapping("")
    public void addPublish(@RequestBody final Publish p) {
        publishRepository.save(p);
    }

    /** Sets the employee’s last name.
     * @param userId userId.
     * @param publishId publishId.
     */
    @ApiOperation(value = "Add comment to a publish id by user id")
    @PostMapping("/{userId}/{publishId}")
    public void addCommentToPublish(@PathVariable final Integer userId,
                                    @PathVariable final Integer publishId) {
        Comment comment = commentRepository.findById(publishId)
                .orElseThrow(() -> new HttpClientErrorException(
                        HttpStatus.BAD_REQUEST));
        Publish publish = getById(publishId);
        comment.setPublish(publish);
        publish.getComments().add(comment);
        commentRepository.save(comment);
        publishRepository.save(publish);
    }

    /** Gets all publish.
     * @return A list of publish.
     */
    @ApiOperation(value = "Get all publish")
    @GetMapping("")
    public List<Publish> getAllPublish() {
        return publishRepository.findAll();
    }

    /** Gets a specific publish by id.
     * @return A specific publish by id.
     * @param id publish id.
     */
    @ApiOperation(value = "Get a specific publish by id")
    @GetMapping("/{id}")
    public Publish getById(@PathVariable final Integer id) {
        return publishRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(
                        HttpStatus.BAD_REQUEST,
                        String.format(PUBLISH_NOT_FOUND, id)));
    }

    /** Delete comment wiht scheduler.
     */
    @Scheduled(fixedRate = FIXED_RATE)
    private void deleteComment() {
        commentRepository.deleteComment();
    }

    /** Gets publish async.
     * @return A response entity for publish.
     */
    @ApiOperation(value = "Get async publish")
    @GetMapping("/async")
    public ResponseEntity<?> getAsync() {
        return ResponseEntity.status(HttpStatus.OK).body(
                publishService.getPublish());
    }
}
