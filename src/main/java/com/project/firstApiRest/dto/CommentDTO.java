/**
 * @since 1.0
 * @author Gonzalo Orellano
 * @version 1.0
 */
package com.project.firstApiRest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**Comment DTO.**/
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
