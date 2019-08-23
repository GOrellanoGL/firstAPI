package com.project.firstAPI.repository;

import com.project.firstAPI.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM comment WHERE (timeStampDiff(day, date_Comment, now())) > 5;", nativeQuery = true)
    void deleteComment();
}
