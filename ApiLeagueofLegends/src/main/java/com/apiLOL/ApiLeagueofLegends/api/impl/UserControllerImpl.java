package com.apiLOL.ApiLeagueofLegends.api.impl;

import com.apiLOL.ApiLeagueofLegends.api.spec.UserController;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.UserResponse;
import com.apiLOL.ApiLeagueofLegends.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserControllerImpl implements UserController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping
    public UserResponse newUser(@RequestBody UserResponse user){
        return userService.newUser(user);
    }
    @RequestMapping("/{userId}")
    public UserResponse getById(@PathVariable("userId") String userId){
        return userService.getById(userId);
    }

}
