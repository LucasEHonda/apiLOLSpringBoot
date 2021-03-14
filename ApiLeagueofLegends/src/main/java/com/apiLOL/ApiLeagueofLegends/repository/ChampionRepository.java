package com.apiLOL.ApiLeagueofLegends.repository;

import com.apiLOL.ApiLeagueofLegends.domain.Champion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChampionRepository extends MongoRepository<Champion, String> {

}
