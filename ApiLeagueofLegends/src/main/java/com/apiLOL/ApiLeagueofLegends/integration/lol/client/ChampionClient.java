package com.apiLOL.ApiLeagueofLegends.integration.lol.client;

import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.ChampionsListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "championns", url = "http://ddragon.leagueoflegends.com/cdn/11.3.1/data/pt_BR/champion.json")
public interface ChampionClient {
    @GetMapping()
    String getChampions();

}
