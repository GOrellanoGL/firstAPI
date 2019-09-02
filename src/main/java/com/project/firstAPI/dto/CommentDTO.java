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
public class CommentDTO {
    /**Id.**/
    @Id
    private Integer id;
    /**Description.**/
    private String description;
    /**Owner.**/
    private String owner;
}
