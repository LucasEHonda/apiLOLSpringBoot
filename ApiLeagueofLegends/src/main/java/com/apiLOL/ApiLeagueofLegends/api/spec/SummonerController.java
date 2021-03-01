package com.apiLOL.ApiLeagueofLegends.api.spec;


import com.apiLOL.ApiLeagueofLegends.api.dto.response.HistoryMatchesResponse;
import com.apiLOL.ApiLeagueofLegends.api.dto.response.LastTenMatchesResponse;
import com.apiLOL.ApiLeagueofLegends.domain.Summoner;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface SummonerController {
    public Summoner getSummonerByName(@PathVariable("summonerName") String summonerName);

    public LastTenMatchesResponse getMatchList(@PathVariable("summonerName") String summonerName);

    public List<HistoryMatchesResponse> getMatchDetails(@PathVariable("summonerName") String summonerName);

}
