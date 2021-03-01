package com.apiLOL.ApiLeagueofLegends.integration.lol.service.spec;

import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.ChampionResponse;

import java.util.List;



public interface ChampionService {
    public ChampionResponse getById(String id);
    public ChampionResponse newChampion(ChampionResponse championResponse);
}
