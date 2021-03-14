package com.apiLOL.ApiLeagueofLegends.integration.lol.client;

import com.apiLOL.ApiLeagueofLegends.domain.Summoner;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.MatchResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.RankedInfoResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.SummonerMatchListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "summonerName", url = "https://br1.api.riotgames.com/lol/")
public interface SummonerClient {

    @GetMapping("summoner/v4/summoners/by-name/{summonerName}")
    Summoner getSummonerByName(@PathVariable("summonerName") String summonerName, @RequestParam(value="api_key") String apiKey);

    @GetMapping("match/v4/matchlists/by-account/{accountID}")
    SummonerMatchListResponse getMatchLIstByAccountID(@PathVariable("accountID") String accountID, @RequestParam(value="api_key") String apiKey);

    @GetMapping("match/v4/matches/{matchId}")
    MatchResponse getMatchDetails(@PathVariable("matchId") String matchId, @RequestParam(value="api_key") String apiKey);
    
    @GetMapping("spectator/v4/active-games/by-summoner/{summoner_id}")
    MatchResponse getMatchOn(@PathVariable("summoner_id") String summoner_id, @RequestParam(value="api_key") String apiKey);

    @GetMapping("league/v4/entries/by-summoner/{summoner_id}")
    List<RankedInfoResponse> getRankedInfo(@PathVariable("summoner_id") String summoner_id, @RequestParam(value="api_key") String apiKey);

}