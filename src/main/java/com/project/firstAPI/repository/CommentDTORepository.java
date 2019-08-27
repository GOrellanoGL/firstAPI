package com.project.firstAPI.repository;

import com.project.firstAPI.dto.CommentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CommentDTORepository extends JpaRepository<CommentDTO, Integer> {
    @Modifying
    @Transactional
    @Query(value = "SELECT comment.id, comment.description, comment.owner FROM comment;", nativeQuery = true)
    List<CommentDTO> getComment();
}
