package com.apiLOL.ApiLeagueofLegends.api.spec;


import com.apiLOL.ApiLeagueofLegends.api.dto.response.HistoryMatchesResponse;
import com.apiLOL.ApiLeagueofLegends.api.dto.response.LastTenMatchesResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface SummonerController {

    public String getSummonerByName(@PathVariable("summonerName") String summonerName);

    public LastTenMatchesResponse getMatchList(@PathVariable("summonerName") String summonerName);

    public List<HistoryMatchesResponse> getMatchDetails(@PathVariable("summonerName") String summonerName);
}
