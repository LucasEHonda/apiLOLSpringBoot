package com.apiLOL.ApiLeagueofLegends.api.spec;

import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.ChampionResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ChampionController {
    public ChampionResponse newChampion(@RequestBody ChampionResponse champion);
}
