package com.apiLOL.ApiLeagueofLegends.api.impl;

import com.apiLOL.ApiLeagueofLegends.api.spec.SummonerController;
import com.apiLOL.ApiLeagueofLegends.api.dto.response.LastMatchesResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.MatchResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.service.impl.SummonerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("LOL")

public class SummonerControllerImpl implements SummonerController {

    @Autowired
    SummonerServiceImpl summonerService;


    @Override
    @GetMapping( path = "account/{summonerName}")
    public String getSummonerByName(@PathVariable("summonerName") String summonerName) {
        return summonerService.getNamePlusLevel(summonerName);
    }

    @Override
    @GetMapping( path = "historico/{summonerName}")
    public LastMatchesResponse getMatchList(@PathVariable("summonerName") String summonerName) {
        return summonerService.getLastTenMatchQuantity(summonerName);
    }


    @GetMapping(path = "historico/detalhes/{matchId}")
    public MatchResponse getMatchDetails(@PathVariable("matchId") String matchId){
        return summonerService.getMatchDetails(matchId);
    }
}