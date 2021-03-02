package com.apiLOL.ApiLeagueofLegends.repository;

import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.UserResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserResponse, String> {
}
