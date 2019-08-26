package com.project.firstAPI.service;

import com.project.firstAPI.model.PublishDTO;
import com.project.firstAPI.repository.PublishDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PublishService {
    @Autowired
    PublishDTORepository publishDTORepository;

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<PublishDTO>> getPublish() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //no deberia de hacer esta cast -- ver
        return (CompletableFuture<List<PublishDTO>>) publishDTORepository.getPublishList();
    }
}
