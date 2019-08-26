package com.project.firstAPI.service;

import com.project.firstAPI.model.CommentDTO;
import com.project.firstAPI.repository.CommentDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CommentService {
    @Autowired
    CommentDTORepository commentDTORepository;

    public CompletableFuture<List<CommentDTO>> getComments() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return CompletableFuture.completedFuture(commentDTORepository.getComment());
    }
}
