package com.project.apirest.repository;

import com.project.apirest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**User repository.**/
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
