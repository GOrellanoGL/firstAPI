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

/**Count commenty by publish repository.**/
@Repository
public interface CountCommentByPublishRepository extends JpaRepository<
        CommentByPublish, Integer> {
    /** Get count list comment by publish.
     * @return A list.
     */
    @Modifying
    @Transactional
    @Query(value = "SELECT c.id, p.title, c.owner, count(c.id) AS count "
            + "FROM comment c "
            + "JOIN publish p ON p.id = c.publish_id GROUP BY c.owner;",
            nativeQuery = true)
    List<CommentByPublish> getCount();

    /** Get count list comment by publish with pagination.
     * @return A list.
     * @param pageable .
     */
    @Transactional
    @Query(value = "SELECT c.id, p.title, c.owner, count(c.id) AS count "
            + "FROM comment c "
            + "JOIN publish p ON p.id = c.publish_id GROUP BY c.owner "
            + "/*#pageable/;",
            nativeQuery = true,
            countQuery = "SELECT (*) from comment")
    Page<CommentByPublish> getCountWithPagination(Pageable pageable);
}
