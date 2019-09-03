/**
 * @since 1.0
 * @author Gonzalo Orellano
 * @version 1.0
 */
package com.project.firstAPI.service;

import com.project.firstAPI.dto.CommentDTO;
import com.project.firstAPI.repository.CommentDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**Comment Service.**/
@Service
public class CommentService {
    /**Sleep.**/
    private final Integer sleepThread = 2000;
    /**CommentDTORepository.**/
    @Autowired
    private CommentDTORepository commentDTORepository;
    /** Gets comments.
     * @return completable future with list.
     */
    public final CompletableFuture<List<CommentDTO>> getComments() {
        try {
            Thread.sleep(sleepThread);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return CompletableFuture.completedFuture(
                commentDTORepository.getComment());
    }

}
