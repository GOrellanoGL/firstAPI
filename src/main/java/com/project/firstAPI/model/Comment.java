/**
 * @since 1.0
 * @author Gonzalo Orellano
 * @version 1.0
 */
package com.project.firstAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.isNull;

/**Comment.**/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    /**ID.**/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**Description.**/
    private String description;
    /**Date comment.**/
    private String dateComment;
    /**Owner.**/
    private String owner;
    /**Publish.**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publish_id", referencedColumnName = "id")
    @JsonIgnore
    private Publish publish;

    /**PrePersist.**/
    @PrePersist
    public final void setTime() {
        if (isNull(this.getDateComment())) {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("dd-mmm-yyyy");
            LocalDateTime localDateTime = LocalDateTime.now();
            this.dateComment = localDateTime.format(formatter);
        }
    }
}
