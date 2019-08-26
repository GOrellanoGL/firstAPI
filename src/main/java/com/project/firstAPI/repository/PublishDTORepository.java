package com.project.firstAPI.repository;

import com.project.firstAPI.model.PublishDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PublishDTORepository extends JpaRepository<PublishDTO, Integer> {
    @Modifying
    @Transactional
    @Query(value = "SELECT publish.title, publish.description, publish.likes FROM publish;", nativeQuery = true)
    List<PublishDTO> getPublishList();
}