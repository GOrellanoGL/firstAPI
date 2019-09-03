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

/**List service.**/
@Service
public class ListService {

    /**Sleep thread.**/
    private final Integer sleepThread = 2000;
    /**UserRepository.**/
    @Autowired
    private
    UserRepository userRepository;
    /**PublishRepository.**/
    @Autowired
    private
    PublishRepository publishRepository;
    /**CommentRepository.**/
    @Autowired
    private
    CommentRepository commentRepository;

    /** Gets all users.
     * @return A completable future for all users.
     */
    @Async("threadPoolTaskExecutor")
    public final CompletableFuture<List<User>> getAllUsers() {
        sleepThread();
        return CompletableFuture.completedFuture(userRepository.findAll());
    }

    /** Gets all publish.
     * @return A completable future for all publish.
     */
    @Async("threadPoolTaskExecutor")
    public final CompletableFuture<List<Publish>> getAllPublish() {
        sleepThread();
        return CompletableFuture.completedFuture(publishRepository.findAll());
    }

    /** Gets all comments.
     * @return A completable future for all comments.
     */
    @Async("threadPoolTaskExecutor")
    public final CompletableFuture<List<Comment>> getAllComments() {
        sleepThread();
        return CompletableFuture.completedFuture(commentRepository.findAll());
    }

    /**Sleep thread.**/
    private void sleepThread() {
        try {
            Thread.sleep(sleepThread);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
