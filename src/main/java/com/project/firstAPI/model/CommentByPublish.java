package com.project.firstAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommentByPublish {
    @Id
    private Integer id;
    private String title;
    private String owner;
    private Integer count;
}
