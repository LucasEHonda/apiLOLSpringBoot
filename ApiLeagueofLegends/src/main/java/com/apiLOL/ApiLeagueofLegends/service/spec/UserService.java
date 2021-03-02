package com.apiLOL.ApiLeagueofLegends.service.spec;

import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.UserResponse;

public interface UserService {
    public UserResponse newUser(UserResponse userResponse);
    public UserResponse getById(String id);
}
