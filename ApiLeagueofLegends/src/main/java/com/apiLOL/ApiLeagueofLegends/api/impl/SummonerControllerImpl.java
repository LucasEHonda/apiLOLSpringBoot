package com.apiLOL.ApiLeagueofLegends.api.impl;

import com.apiLOL.ApiLeagueofLegends.api.dto.response.HistoryMatchesResponse;
import com.apiLOL.ApiLeagueofLegends.api.spec.SummonerController;
import com.apiLOL.ApiLeagueofLegends.api.dto.response.LastTenMatchesResponse;
import com.apiLOL.ApiLeagueofLegends.domain.Summoner;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.ChampionResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.service.impl.ChampionServiceImpl;
import com.apiLOL.ApiLeagueofLegends.integration.lol.service.impl.SummonerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("LOL")

public class SummonerControllerImpl implements SummonerController {

    @Autowired
    SummonerServiceImpl summonerService;
    @Autowired
    ChampionServiceImpl championService;


    @Override
    @GetMapping( path = "account/{summonerName}")
    public Summoner getSummonerByName(@PathVariable("summonerName") String summonerName) {
        return summonerService.getNamePlusLevel(summonerName);
    }

    @Override
    @GetMapping( path = "historico/{summonerName}")
    public LastTenMatchesResponse getMatchList(@PathVariable("summonerName") String summonerName) {
        return summonerService.getLastTenMatchQuantity(summonerName);
    }


    @GetMapping(path = "historico/detalhes/{summonerName}")
    public List<HistoryMatchesResponse> getMatchDetails(@PathVariable("summonerName") String summonerName){
        return summonerService.getMatchDetailsBySummonerName(summonerName);
    }

    @GetMapping(path = "champions/{Id}")
    public ChampionResponse getChampion(@PathVariable("Id") String Id){
        return championService.getById(Id);
    }

    @PostMapping
    public ChampionResponse newChampion(@RequestBody ChampionResponse champion) {
        return championService.newChampion(champion);
        }
    }