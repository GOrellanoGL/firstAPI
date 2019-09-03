package com.project.firstApiRest.repository;

import com.project.firstApiRest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**User repository.**/
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
