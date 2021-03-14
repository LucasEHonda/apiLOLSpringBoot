package com.apiLOL.ApiLeagueofLegends.api.spec;

import com.apiLOL.ApiLeagueofLegends.domain.Champion;
import org.springframework.web.bind.annotation.RequestBody;

public interface ChampionController {
    public Champion newChampion(@RequestBody Champion champion);
}
