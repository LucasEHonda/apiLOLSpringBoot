package com.apiLOL.ApiLeagueofLegends.api.spec;

import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.UserResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserController {
    public UserResponse newUser(@RequestBody UserResponse user);
    public UserResponse getById(@PathVariable("userId") String userId);
}
