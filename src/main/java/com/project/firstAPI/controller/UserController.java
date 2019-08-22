package com.project.firstAPI.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    private static final String PERSON_NOT_FOUND = "User ID not found: %s";

}
