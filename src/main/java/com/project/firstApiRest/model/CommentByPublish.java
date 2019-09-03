/**
 * @since 1.0
 * @author Gonzalo Orellano
 * @version 1.0
 */
package com.project.firstApiRest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**Comment by publish.**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommentByPublish {
    /**Id.**/
    @Id
    private Integer id;
    /**Title.**/
    private String title;
    /**Owner.**/
    private String owner;
    /**Count.**/
    private Integer count;
}
