/**
 * @since 1.0
 * @author Gonzalo Orellano
 * @version 1.0
 */
package com.project.firstAPI.repository;

import com.project.firstAPI.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**User DTO repository.**/
@Repository
public interface UserDTORepository extends JpaRepository<UserDTO, Integer> {
    /**Get users list.
     * @return users list.**/
    @Modifying
    @Transactional
    @Query(value = "SELECT u.id, u.name, u.lastName FROM user;",
            nativeQuery = true)
    List<UserDTO> getUsersList();
}
