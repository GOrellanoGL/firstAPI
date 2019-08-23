package com.project.firstAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lists {
    private List<User> userList;
    private List<Publish> publishList;
    private List<Comment> commentList;
}
