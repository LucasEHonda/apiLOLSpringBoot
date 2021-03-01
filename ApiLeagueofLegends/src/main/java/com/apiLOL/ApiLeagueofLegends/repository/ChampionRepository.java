package com.apiLOL.ApiLeagueofLegends.repository;

import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.ChampionResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChampionRepository extends MongoRepository<ChampionResponse, String> {

}
