package com.project.firstAPI.controller;

import com.project.firstAPI.model.Comment;
import com.project.firstAPI.model.Publish;
import com.project.firstAPI.model.PublishDTO;
import com.project.firstAPI.repository.CommentRepository;
import com.project.firstAPI.repository.PublishRepository;
import com.project.firstAPI.service.CommentService;
import com.project.firstAPI.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequestMapping("/publish")
@RestController
@EnableScheduling
public class PublishController {
    private static final String PUBLISH_NOT_FOUND = "Publish ID not found: %s";

    @Autowired
    PublishRepository publishRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PublishService publishService;

    //Add publish
    @PostMapping("")
    public void addPublish(@RequestBody final Publish p) {
        publishRepository.save(p);
    }

    //Add a comment to the publish
    @PostMapping("/{userId}/{publishId}")
    public void addCommentToPublish(@PathVariable final Integer userId, @PathVariable final Integer publishId) {
        Comment c = commentRepository.findById(publishId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        Publish p = getById(publishId);
        c.setPublish(p);
        p.getComments().add(c);
        commentRepository.save(c);
        publishRepository.save(p);
    }

    //Get all publish
    @GetMapping("")
    public List<Publish> getAllPublish() {
        return publishRepository.findAll();
    }

    //Get specific publish
    @GetMapping("/{id}")
    public Publish getById(@PathVariable final Integer id) {
        return publishRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(PUBLISH_NOT_FOUND, id)));
    }

    //Scheduler
    @Scheduled(fixedRate = 1000)
    private void deleteComment() {
        commentRepository.deleteComment();
    }

    @GetMapping("/async")
    public ResponseEntity<?> getAsync() {
        return ResponseEntity.status(HttpStatus.OK).body(publishService.getPublish());
    }
}
