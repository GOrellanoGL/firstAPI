package com.project.firstApiRest.service;

import com.project.firstApiRest.model.Comment;
import com.project.firstApiRest.model.Publish;
import com.project.firstApiRest.model.User;
import com.project.firstApiRest.repository.CommentRepository;
import com.project.firstApiRest.repository.PublishRepository;
import com.project.firstApiRest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

/**List service.**/
@Service
public class ListService {
    /**Logger.**/
    private static Logger logger = Logger.getLogger(
            ListService.class.getName());
    /**Sleep thread.**/
    private static final Integer SLEEPTHREAD = 2000;
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
            Thread.sleep(SLEEPTHREAD);
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "Interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

}
