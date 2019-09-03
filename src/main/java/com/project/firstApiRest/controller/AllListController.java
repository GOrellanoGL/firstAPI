package com.project.firstApiRest.controller;

import com.project.firstApiRest.model.Comment;
import com.project.firstApiRest.model.Publish;
import com.project.firstApiRest.model.User;
import com.project.firstApiRest.service.ListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**All list controller.**/
@RequestMapping("/lists")
@RestController
@EnableScheduling
@Api(value = "All list (User, Publish and Comments) management system",
        description = "Operations pertaining to all list management system")
public class AllListController {
    /**Service List.**/
    @Autowired
    private ListService listService;

    /** Get all list wit user, publish and comment.
     * @return A responseEntity with the three list joined.
     */
    @ApiOperation(value = "Get all list wit user, publish and comment")
    @GetMapping("/allContent")
    public final ResponseEntity<?> getAsync() {
        CompletableFuture<List<User>> userList =
                listService.getAllUsers();
        CompletableFuture<List<Publish>> publishList =
                listService.getAllPublish();
        CompletableFuture<List<Comment>> commentList =
                listService.getAllComments();

        return ResponseEntity.status(HttpStatus.OK).body(userList.join() + " "
                + publishList.join() + " " + commentList.join());
    }
}
