/**
 * @since 1.0
 * @author Gonzalo Orellano
 * @version 1.0
 */
package com.project.apirest.repository;

import com.project.apirest.dto.CommentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**CommentDTO repository.**/
@Repository
public interface CommentDTORepository extends JpaRepository<
        CommentDTO, Integer> {
    /** Gets Comment.
     * @return A list with commentDTO.
     */
    @Modifying
    @Transactional
    @Query(value = "SELECT comment.id, comment.description, comment.owner "
            + "FROM comment;", nativeQuery = true)
    List<CommentDTO> getComment();
}
