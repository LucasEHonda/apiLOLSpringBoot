package com.apiLOL.ApiLeagueofLegends.integration.lol.client;

import com.apiLOL.ApiLeagueofLegends.domain.Summoner;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.SummonerMatchListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "summonerName", url = "https://br1.api.riotgames.com/lol/")
public interface SummonerClient {
    @GetMapping("summoner/v4/summoners/by-name/{summonerName}")
    Summoner getSummonerByName(@PathVariable("summonerName") String summonerName, @RequestParam(value="api_key") String apiKey);

    @GetMapping("match/v4/matchlists/by-account/{accountID}")
    SummonerMatchListResponse getMatchLIstByAccountID(@PathVariable("accountID") String accountID, @RequestParam(value="api_key") String apiKey);
}