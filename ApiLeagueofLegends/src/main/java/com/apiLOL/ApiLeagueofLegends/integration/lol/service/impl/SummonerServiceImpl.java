package com.apiLOL.ApiLeagueofLegends.integration.lol.service.impl;

import com.apiLOL.ApiLeagueofLegends.api.dto.response.HistoryMatchesResponse;
import com.apiLOL.ApiLeagueofLegends.api.dto.response.MatchOnInfoResponse;
import com.apiLOL.ApiLeagueofLegends.api.dto.response.SummonerResponse;
import com.apiLOL.ApiLeagueofLegends.domain.Summoner;
import com.apiLOL.ApiLeagueofLegends.integration.lol.client.SummonerClient;
import com.apiLOL.ApiLeagueofLegends.api.dto.response.LastTenMatchesResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.*;
import com.apiLOL.ApiLeagueofLegends.integration.lol.service.spec.MatchEngine;
import com.apiLOL.ApiLeagueofLegends.integration.lol.service.spec.SummonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SummonerServiceImpl implements SummonerService {
    @Value("${api-lol.api-key}")
    String apiKey;


    @Autowired
    SummonerClient summonerClient;

    @Autowired
    ChampionServiceImpl championService;

    @Autowired
    MatchEngine matchEngine;

    @Override
    public Summoner getNamePlusLevel(String summonerName) {
        return summonerClient.getSummonerByName(summonerName,apiKey);
    }

    public LastTenMatchesResponse getLastTenMatchQuantity(String summonerName){

        Summoner summoner = summonerClient.getSummonerByName(summonerName,apiKey);
        SummonerMatchListResponse summonerHistory = summonerClient.getMatchLIstByAccountID(summoner.getAccountId(),apiKey);
        List<MatchListResponse> matches = summonerHistory.getMatches();
        // LISTA DE IDS de campeões
        List<Integer> lastTenMatches = new ArrayList<>();
        Map<Integer, Integer> champCount = new TreeMap<>();
        List<Integer> timesPlayed = new ArrayList<>();
        List<Integer> championId = new ArrayList<>();
        Date dataAgora = new Date();

        //long matchesPlayed = matches.stream().filter(match -> ((dataAgora.getTime() - match.getTimestamp()) / (1000*60*60*24)) <= 10 ).count();
        //Aqui nos contamos o numero de partidas nos ultimos 10 dias.

        for (MatchListResponse match : matches) {
            if (((dataAgora.getTime() - match.getTimestamp()) / (1000 * 60 * 60 * 24)) <= 10) {
                lastTenMatches.add(match.getChampion());
                //Aqui nos armazenamos numa lista numero de partidas nos ultimos 10.
            }
        }

        // Contagem de campeões usados, pode ser ordenado também.
        Map<Integer, Long> countChampions = lastTenMatches.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

//        lastTenMatches.forEach(champion -> champCount.compute(champion, (k, v) -> v == null ? 1 : ++v));
//
//        //Aqui nos armazenamos num dict o numero de partidas jogadas por campeao nos ultimos 10 dias.
//        for (Map.Entry<Integer, Integer> champion : champCount.entrySet()) {
//            timesPlayed.add(champion.getValue());
//            championId.add(champion.getKey());
//            //Aqui nos separamos em duas listas o dict feito acima.
//        }

        String champName = championService.getChampionName(Integer.toString(championId.get(timesPlayed.indexOf(Collections.max(timesPlayed)))));

        return LastTenMatchesResponse.builder().averageTimeSpent(((lastTenMatches.size()*30)/60)+"h").summonerName(summoner.getName()).matchesPlayedLastTenDays(lastTenMatches.size()).mustPlayedChamp(champName).build();
}

    public List<HistoryMatchesResponse> getMatchDetailsBySummonerName(String summonerName){
        Summoner summoner = summonerClient.getSummonerByName(summonerName,apiKey);
        List<MatchListResponse> matches = summonerClient.getMatchLIstByAccountID(summoner.getAccountId(),apiKey).getMatches();
        List<HistoryMatchesResponse> listHistoryMatchesResponses = new ArrayList<>();
        //o for abaixo seria melhor com um foreach

        for (MatchListResponse match: matches) {
            MatchResponse matchDetails = summonerClient.getMatchDetails(Long.toString(match.getGameId()), apiKey);
            Optional<MatchParticipantsResponse> playerInMatch = matchDetails.getParticipants()
                .stream()
                .filter(p -> p.getSummonerName().equals(summonerName)).findFirst();

            playerInMatch.ifPresent(matchParticipantsResponse ->
            listHistoryMatchesResponses.add(matchEngine.buildMatchInfo(matchParticipantsResponse, matchDetails, matchDetails.getParticipants())));
        }
       return listHistoryMatchesResponses;
    }

    public MatchOnInfoResponse getActiveMatch(String summonerName){

        MatchResponse matchResponse = summonerClient.getMatchOn(summonerClient.getSummonerByName(summonerName,apiKey).getId(),apiKey);
        List<SummonerResponse>summoners = new ArrayList<>();

        for (int i = 0; i < matchResponse.getParticipants().size(); i++) {
            summoners.add(SummonerResponse.builder()
                    .championName(championService.getChampionName(Integer.toString(matchResponse.getParticipants().get(i).getChampionId())))
                    .rankedInfos(summonerClient.getRankedInfo(summonerClient.getSummonerByName(matchResponse.getParticipants().get(i).getSummonerName(),apiKey).getId(),apiKey))
                    .team((matchResponse.getParticipants().get(i).getTeamId() == 100) ? "Blue Team" : "Red Team")
                    .summonerName(matchResponse.getParticipants().get(i).getSummonerName())
                    .build());
        }
        return MatchOnInfoResponse.builder().summoners(summoners).gameMode("5x5 Summoner's Rift").gameDuration(Integer.toString((int) matchResponse.getGameLength()/60)).gameStartTime((new Date(matchResponse.getGameStartTime())) + ".").build();
    }

    public List<RankedInfoResponse> getRankedInfo(String summonerName){
        return summonerClient.getRankedInfo(summonerClient.getSummonerByName(summonerName,apiKey).getId(),apiKey);
    }
}