package com.apiLOL.ApiLeagueofLegends.integration.lol.service.spec;

import com.apiLOL.ApiLeagueofLegends.domain.Champion;


public interface ChampionService {
    Champion getById(String id);
    Champion newChampion(Champion champion);
    String getChampionName(String champId);
}
