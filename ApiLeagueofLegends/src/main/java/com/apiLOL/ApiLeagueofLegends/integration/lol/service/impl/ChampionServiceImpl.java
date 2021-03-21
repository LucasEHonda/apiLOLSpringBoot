package com.apiLOL.ApiLeagueofLegends.integration.lol.service.impl;

import com.apiLOL.ApiLeagueofLegends.domain.Champion;
import com.apiLOL.ApiLeagueofLegends.integration.lol.service.spec.ChampionService;
import com.apiLOL.ApiLeagueofLegends.repository.ChampionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChampionServiceImpl implements ChampionService {

    @Autowired
    private ChampionRepository championRepository;

    @Override
    public Champion getById(String id){
        return this.championRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Non-existent champion"));
    }

    @Override
    public Champion newChampion(Champion champion){
           return this.championRepository.save(champion);
    }

    public String getChampionName(String champId){
        return this.getById(champId).getName();
    }

}
