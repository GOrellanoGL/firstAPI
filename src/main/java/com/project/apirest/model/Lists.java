package com.project.apirest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**Lists.**/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lists {
    /**User List.**/
    private List<User> userList;
    /**Publish List.**/
    private List<Publish> publishList;
    /**Comment List.**/
    private List<Comment> commentList;
}
