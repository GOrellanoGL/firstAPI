package com.project.firstApiRest.service;

import com.project.firstApiRest.dto.PublishDTO;
import com.project.firstApiRest.repository.PublishDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Publish service.**/
@Service
public class PublishService {
    /**Logger.**/
    private static Logger logger = Logger.getLogger(
            PublishService.class.getName());
    /**Sleep thread.**/
    private static final Integer SLEEPTHREAD = 2000;
    /**PublishDTO repository.**/
    @Autowired
    private PublishDTORepository publishDTORepository;

    /** Gets publish dto list.
     * @return completable future of publish list.
     */
    @Async("threadPoolTaskExecutor")
    public final CompletableFuture<List<PublishDTO>> getPublish() {
        try {
            Thread.sleep(SLEEPTHREAD);
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "Interrupted", e);
            Thread.currentThread().interrupt();
        }

        return CompletableFuture.completedFuture(
                publishDTORepository.getPublishList());
    }
}
