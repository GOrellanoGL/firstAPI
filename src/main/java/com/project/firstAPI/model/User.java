package com.project.firstAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import java.util.List;

/**User.**/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**Id.**/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**Name.**/
    private String name;
    /**Last name.**/
    private String lastName;
    /**Browser.**/
    private String browser;
    /**Publish.**/
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, mappedBy = "user")
    private List<Publish> publishs;
}
