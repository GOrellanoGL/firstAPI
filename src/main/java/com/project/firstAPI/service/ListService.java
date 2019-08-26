package com.project.firstAPI.service;

import com.project.firstAPI.model.Comment;
import com.project.firstAPI.model.Publish;
import com.project.firstAPI.model.User;
import com.project.firstAPI.repository.CommentRepository;
import com.project.firstAPI.repository.PublishRepository;
import com.project.firstAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ListService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PublishRepository publishRepository;

    @Autowired
    CommentRepository commentRepository;

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<User>> getAllUsers() {
        sleepThread();
        return CompletableFuture.completedFuture(userRepository.findAll());
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<Publish>> getAllPublish() {
        sleepThread();
        return CompletableFuture.completedFuture(publishRepository.findAll());
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<Comment>> getAllComments() {
        sleepThread();
        return CompletableFuture.completedFuture(commentRepository.findAll());
    }

    private void sleepThread() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
