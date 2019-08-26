package com.project.firstAPI.repository;

import com.project.firstAPI.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserDTORepository extends JpaRepository<UserDTO, Integer> {
    @Modifying
    @Transactional
    @Query(value = "SELECT u.id, u.name, u.last_name FROM user;", nativeQuery = true)
    List<UserDTO> getUsersList();
}
