package com.project.firstAPI.service;

import com.project.firstAPI.dto.UserDTO;
import com.project.firstAPI.repository.UserDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**User service.**/
@Service
public class UserService {
    /**Sleep thread.**/
    private final Integer sleepThread = 2000;
    /**UserDTO repository.**/
    @Autowired
    private UserDTORepository userDTORepository;

    /** Gets users dto list.
     * @return completable future of users list.
     */
    @Async("threadPoolTaskExecutor")
    public final CompletableFuture<UserDTO> getUsers() {
        try {
            Thread.sleep(sleepThread);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<UserDTO> users = userDTORepository.getUsersList();
        //CompletableFuture.completedFuture(users);
        return null;
    }
}
