package com.project.firstAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.isNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String dateComment;
    private String owner;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publish_id", referencedColumnName = "id")
    @JsonIgnore
    private Publish publish;

    @PrePersist
    public void setTime() {
        if (isNull(this.getDateComment())) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mmm-yyyy");
            LocalDateTime localDateTime = LocalDateTime.now();
            this.dateComment = localDateTime.format(formatter);
        }
    }
}
