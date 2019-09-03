package com.project.firstApiRest.repository;

import com.project.firstApiRest.dto.PublishDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**Publish DTO repository.**/
@Repository
public interface PublishDTORepository extends JpaRepository<
        PublishDTO, Integer> {
    /**Get publish list.
     * @return publish list.**/
    @Modifying
    @Transactional
    @Query(value = "SELECT publish.id, publish.title, publish.description, "
            + "publish.likes FROM publish;", nativeQuery = true)
    List<PublishDTO> getPublishList();
}
