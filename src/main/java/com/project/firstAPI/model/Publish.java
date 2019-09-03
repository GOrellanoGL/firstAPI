package com.project.firstAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;

/**Publish.**/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publish {
    /**Id.**/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**Title.**/
    private String title;
    /**Description.**/
    private String description;
    /**Date publish.**/
    private LocalDateTime datePublish;
    /**Photo.**/
    private String photo;
    /**Likes.**/
    private Integer likes;
    /**User.**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;
    /**Comments.**/
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            mappedBy = "publish")
    private List<Comment> comments;

    /**PrePersist.**/
    @PrePersist
    public final void setTime() {
        if (isNull(this.getDatePublish())) {
            this.datePublish = LocalDateTime.now();
        }
    }
}
