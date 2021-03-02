package com.apiLOL.ApiLeagueofLegends.service.impl;


import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.UserResponse;
import com.apiLOL.ApiLeagueofLegends.repository.UserRepository;
import com.apiLOL.ApiLeagueofLegends.service.spec.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse newUser(UserResponse userResponse){
        return this.userRepository.save(userResponse);
    }
    @Override
    public UserResponse getById(String id){
        return this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Non-existent champion"));
    }
}
