package com.project.firstAPI.controller;

import com.project.firstAPI.model.Comment;
import com.project.firstAPI.model.Publish;
import com.project.firstAPI.model.User;
import com.project.firstAPI.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequestMapping("/lists")
@RestController
@EnableScheduling
public class AllListController {
    @Autowired
    ListService listService;

    @GetMapping("/allContent")
    public ResponseEntity<?> getAsync() {
        CompletableFuture<List<User>> userList = listService.getAllUsers();
        CompletableFuture<List<Publish>> publishList = listService.getAllPublish();
        CompletableFuture<List<Comment>> commentList = listService.getAllComments();

        return ResponseEntity.status(HttpStatus.OK).body(userList.join() + " " + publishList.join() + " " + commentList.join());
    }
}
