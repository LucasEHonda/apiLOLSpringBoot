package com.apiLOL.ApiLeagueofLegends.integration.lol.service.impl;

import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.ChampionResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.service.spec.ChampionService;
import com.apiLOL.ApiLeagueofLegends.repository.ChampionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampionServiceImpl implements ChampionService {

    @Autowired
    private ChampionRepository championRepository;

    @Override
    public ChampionResponse getById(String id){
        return this.championRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Non-existent champion"));
    }

    @Override
    public ChampionResponse newChampion(ChampionResponse championResponse){
           return this.championRepository.save(championResponse);
    }

}
