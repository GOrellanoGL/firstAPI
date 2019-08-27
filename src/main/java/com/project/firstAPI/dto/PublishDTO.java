package com.project.firstAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PublishDTO {
    @Id
    private Integer id;
    private String title;
    private String description;
    private Integer likes;
}
