package com.project.firstApiRest.service;

import com.project.firstApiRest.dto.UserDTO;
import com.project.firstApiRest.repository.UserDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

/**User service.**/
@Service
public class UserService {
    /**Logger.**/
    private static Logger logger = Logger.getLogger(
            UserService.class.getName());
    /**Sleep thread.**/
    private static final Integer SLEEPTHREAD = 2000;
    /**UserDTO repository.**/
    @Autowired
    private UserDTORepository userDTORepository;

    /** Gets users dto list.
     * @return completable future of users list.
     */
    @Async("threadPoolTaskExecutor")
    public final CompletableFuture<List<UserDTO>> getUsers() {
        try {
            Thread.sleep(SLEEPTHREAD);
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "Interrupted", e);
            Thread.currentThread().interrupt();
        }
        return CompletableFuture.completedFuture(
                userDTORepository.getUsersList());
    }
}
