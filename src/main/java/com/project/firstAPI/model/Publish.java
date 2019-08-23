package com.project.firstAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime datePublish;
    private String photo;
    private Integer likes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "publish")
    private List<Comment> comments;

    @PrePersist
    public void setTime() {
        if (isNull(this.getDatePublish())) {
            this.datePublish = LocalDateTime.now();
        }
    }
}
