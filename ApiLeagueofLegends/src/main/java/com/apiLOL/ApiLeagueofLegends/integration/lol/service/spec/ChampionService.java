package com.apiLOL.ApiLeagueofLegends.integration.lol.service.spec;

import com.apiLOL.ApiLeagueofLegends.domain.Champion;


public interface ChampionService {
    public Champion getById(String id);
    public Champion newChampion(Champion champion);
}
