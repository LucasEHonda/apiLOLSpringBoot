package com.apiLOL.ApiLeagueofLegends.integration.lol.service.spec;

import com.apiLOL.ApiLeagueofLegends.api.dto.response.HistoryMatchesResponse;
import com.apiLOL.ApiLeagueofLegends.api.dto.response.LastTenMatchesResponse;
import com.apiLOL.ApiLeagueofLegends.api.dto.response.MatchOnInfoResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.MatchResponse;
import com.apiLOL.ApiLeagueofLegends.domain.Summoner;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.RankedInfoResponse;


import java.util.List;

public interface SummonerService {

    public Summoner getNamePlusLevel(String summonerName);
    public LastTenMatchesResponse getLastTenMatchQuantity(String summonerName);
    public List<HistoryMatchesResponse> getMatchDetailsBySummonerName(String summonerName);
    public MatchOnInfoResponse getActiveMatch(String summonerName);
    public List<RankedInfoResponse> getRankedInfo(String summonerName);
}
