package com.project.firstAPI.service;

import com.project.firstAPI.dto.PublishDTO;
import com.project.firstAPI.repository.PublishDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PublishService {
    /**Sleep thread.**/
    private static final Integer SLEEP_THREAD = 2000;
    /**PublishDTO repository.**/
    @Autowired
    private PublishDTORepository publishDTORepository;

    /** Gets publish dto list.
     * @return completable future of publish list.
     */
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<PublishDTO>> getPublish() {
        try {
            Thread.sleep(SLEEP_THREAD);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return (CompletableFuture<List<PublishDTO>>)
                publishDTORepository.getPublishList();
    }
}
