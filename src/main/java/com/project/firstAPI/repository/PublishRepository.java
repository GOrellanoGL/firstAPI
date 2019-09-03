package com.project.firstAPI.repository;

import com.project.firstAPI.model.Publish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**Publish repository.**/
@Repository
public interface PublishRepository extends JpaRepository<Publish, Integer> {
}
