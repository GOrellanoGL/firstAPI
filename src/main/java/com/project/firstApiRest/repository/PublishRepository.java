package com.project.firstApiRest.repository;

import com.project.firstApiRest.model.Publish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**Publish repository.**/
@Repository
public interface PublishRepository extends JpaRepository<Publish, Integer> {
}
