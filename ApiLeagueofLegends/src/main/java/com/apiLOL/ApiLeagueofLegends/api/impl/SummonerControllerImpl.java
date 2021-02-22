package com.apiLOL.ApiLeagueofLegends.api.impl;

import com.apiLOL.ApiLeagueofLegends.api.spec.SummonerController;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.MatchListResponse;
import com.apiLOL.ApiLeagueofLegends.domain.Summoner;
import com.apiLOL.ApiLeagueofLegends.integration.lol.client.SummonerClient;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.SummonerMatchListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("account")

public class SummonerControllerImpl implements SummonerController {

    @Autowired
    SummonerClient summonerClient;

    @Value("${api-lol.api-key}")
    String apiKey;

    @Override
    @GetMapping( path = "/{summonerName}")
    public String getSummonerByName(@PathVariable("summonerName") String summonerName) {
        Summoner invocador = summonerClient.getSummonerByName(summonerName,apiKey);
        return (invocador.getName() + " " + "Nivel: " +invocador.getSummonerLevel());
    }

    @Override
    @GetMapping( path = "historico/{summonerName}")
    public String getMatchlist(@PathVariable("summonerName") String summonerName) {
        Summoner invocador = summonerClient.getSummonerByName(summonerName,apiKey);
        SummonerMatchListResponse historicoInvocador = summonerClient.getMatchLIstByAccountID(invocador.getAccountId(),apiKey);
        List<MatchListResponse> partidas = historicoInvocador.getMatches();

        Date dataAgora = new Date();

        long dayssss = partidas.stream().filter(partida -> ((dataAgora.getTime() - partida.getTimestamp()) / (1000*60*60*24)) < 10 ).count();

        return ("Ola " + invocador.getName() + " nos ultimos 10 dias voce jogou " + dayssss + " Partidas. Parabens seu viciado.");
    }
}