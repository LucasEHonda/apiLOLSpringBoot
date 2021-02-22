package com.apiLOL.ApiLeagueofLegends.integration.lol.service.impl;

import com.apiLOL.ApiLeagueofLegends.domain.Summoner;
import com.apiLOL.ApiLeagueofLegends.integration.lol.client.SummonerClient;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.MatchListResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.SummonerMatchListResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.service.spec.SummonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SummonerServiceImpl implements SummonerService {
    @Value("${api-lol.api-key}")
    String apiKey;


    @Autowired
    SummonerClient summonerClient;


    @Override
    public String getNamePlusLevel(String summonerName) {
        Summoner summoner = summonerClient.getSummonerByName(summonerName,apiKey);
        return (summoner.getName() + " " + "Nivel: " + summoner.getSummonerLevel());
    }

    public String getLastTenMatchQuantity(String summonerName){
        Summoner summoner = summonerClient.getSummonerByName(summonerName,apiKey);
        SummonerMatchListResponse summonerHistory = summonerClient.getMatchLIstByAccountID(summoner.getAccountId(),apiKey);
        List<MatchListResponse> matches = summonerHistory.getMatches();

        Date dataAgora = new Date();

        long days = matches.stream().filter(match -> ((dataAgora.getTime() - match.getTimestamp()) / (1000*60*60*24)) < 10 ).count();

        return ("Ola " + summoner.getName() + " nos ultimos 10 dias voce jogou " + days + " Partidas. Parabens seu viciado.");

    }
}
