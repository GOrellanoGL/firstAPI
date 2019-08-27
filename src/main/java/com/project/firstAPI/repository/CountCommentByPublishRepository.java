package com.project.firstAPI.repository;

import com.project.firstAPI.model.CommentByPublish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CountCommentByPublishRepository extends JpaRepository<CommentByPublish, Integer> {
    @Modifying
    @Transactional
    @Query(value = "SELECT c.id, p.title, c.owner, count(c.id) AS count from comment c " +
            "JOIN publish p ON p.id = c.publish_id GROUP BY c.owner;", nativeQuery = true)
    List<CommentByPublish> getCount();

    @Transactional
    @Query(value = "SELECT c.id, p.title, c.owner, count(c.id) AS count from comment c " +
            "JOIN publish p ON p.id = c.publish_id GROUP BY c.owner /*#pageable/;", nativeQuery = true,
            countQuery = "SELECT (*) from comment")
    Page<CommentByPublish> getCountWithPagination(Pageable pageable);
}
