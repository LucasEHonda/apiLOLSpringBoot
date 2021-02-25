package com.apiLOL.ApiLeagueofLegends.api.spec;


import com.apiLOL.ApiLeagueofLegends.api.dto.response.LastMatchesResponse;
import org.springframework.web.bind.annotation.PathVariable;

public interface SummonerController {

    public String getSummonerByName(@PathVariable("summonerName") String summonerName);

    public LastMatchesResponse getMatchList(@PathVariable("summonerName") String summonerName);
}
