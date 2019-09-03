package com.project.firstAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**Publish DTO.**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PublishDTO {
    /**Id.**/
    @Id
    private Integer id;
    /**Title.**/
    private String title;
    /**Description.**/
    private String description;
    /**Likes.**/
    private Integer likes;
}
