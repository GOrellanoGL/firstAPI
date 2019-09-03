/**
 * @since 1.0
 * @author Gonzalo Orellano
 * @version 1.0
 */
package com.project.firstApiRest.service;

import com.project.firstApiRest.dto.CommentDTO;
import com.project.firstApiRest.repository.CommentDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Comment Service.**/
@Service
public class CommentService {
    /**Logger.**/
    private static Logger logger = Logger.getLogger(
            CommentService.class.getName());
    /**Sleep Thread.**/
    private static final int SLEEPTHREAD = 2000;
    /**CommentDTORepository.**/
    @Autowired
    private CommentDTORepository commentDTORepository;
    /** Gets comments.
     * @return completable future with list.
     */
    public final CompletableFuture<List<CommentDTO>> getComments() {
        try {
            Thread.sleep(SLEEPTHREAD);
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "Interrupted", e);
            Thread.currentThread().interrupt();
        }

        return CompletableFuture.completedFuture(
                commentDTORepository.getComment());
    }

}
