package com.project.firstAPI.service;

import com.project.firstAPI.dto.UserDTO;
import com.project.firstAPI.repository.UserDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService {
    @Autowired
    UserDTORepository userDTORepository;

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<UserDTO> getUsers() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<UserDTO> users = userDTORepository.getUsersList();
        return null;//CompletableFuture.completedFuture(users);
    }
}
