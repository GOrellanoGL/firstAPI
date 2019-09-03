/**
 * @since 1.0
 * @author Gonzalo Orellano
 * @version 1.0
 */
package com.project.firstAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**User DTO.**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserDTO {
    /**Id.**/
    @Id
    private Integer id;
    /**Name.**/
    private String name;
    /**Last name.**/
    private String lastName;
}
