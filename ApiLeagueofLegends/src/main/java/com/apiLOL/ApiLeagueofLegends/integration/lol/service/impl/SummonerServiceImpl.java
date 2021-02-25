package com.apiLOL.ApiLeagueofLegends.integration.lol.service.impl;

import com.apiLOL.ApiLeagueofLegends.api.dto.response.HistoryMatchesResponse;
import com.apiLOL.ApiLeagueofLegends.domain.Summoner;
import com.apiLOL.ApiLeagueofLegends.integration.lol.client.ChampionClient;
import com.apiLOL.ApiLeagueofLegends.integration.lol.client.SummonerClient;
import com.apiLOL.ApiLeagueofLegends.api.dto.response.LastTenMatchesResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.*;
import com.apiLOL.ApiLeagueofLegends.integration.lol.service.spec.SummonerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SummonerServiceImpl implements SummonerService {
    @Value("${api-lol.api-key}")
    String apiKey;


    @Autowired
    SummonerClient summonerClient;

    @Autowired
    ChampionClient championClient;


    @Override
    public String getNamePlusLevel(String summonerName) {
        Summoner summoner = summonerClient.getSummonerByName(summonerName,apiKey);
        return (summoner.getName() + " " + "Nivel: " + summoner.getSummonerLevel());
    }

    public LastTenMatchesResponse getLastTenMatchQuantity(String summonerName){

        Summoner summoner = summonerClient.getSummonerByName(summonerName,apiKey);
        SummonerMatchListResponse summonerHistory = summonerClient.getMatchLIstByAccountID(summoner.getAccountId(),apiKey);
        List<MatchListResponse> matches = summonerHistory.getMatches();
        List<Integer> lastTenMatches = new ArrayList<>();
        Map<Integer, Integer> champCount = new TreeMap<>();
        List<Integer> timesPlayed = new ArrayList<>();
        List<Integer> championId = new ArrayList<>();
        Date dataAgora = new Date();
        String championsListResponse = championClient.getChampions();
        ObjectMapper objectMapper = new ObjectMapper();
        String champName = "";
        String champKey = "";

        //long matchesPlayed = matches.stream().filter(match -> ((dataAgora.getTime() - match.getTimestamp()) / (1000*60*60*24)) <= 10 ).count();
        //Aqui nos contamos o numero de partidas nos ultimos 10 dias.

        for (int i = 0; i<matches.size();i++){
            if (((dataAgora.getTime() - matches.get(i).getTimestamp()) / (1000*60*60*24)) <= 10){
                lastTenMatches.add(matches.get(i).getChampion());
                //Aqui nos armazenamos numa lista numero de partidas nos ultimos 10.
            }
        }


        lastTenMatches.forEach(champion -> champCount.compute(champion, (k, v) -> v == null ? 1 : ++v));
        //Aqui nos armazenamos num dict o numero de partidas jogadas por campeao nos ultimos 10 dias.
        for (Map.Entry<Integer, Integer> champion : champCount.entrySet()) {
            timesPlayed.add(champion.getValue());
            championId.add(champion.getKey());
            //Aqui nos separamos em duas listas o dict feito acima.
        }

        try {
            JsonNode jsonnode = objectMapper.readTree(championsListResponse);
            for (Iterator<String> it = jsonnode.get("data").fieldNames(); it.hasNext();) {
                String champ = it.next();
                champName =jsonnode.get("data").get(champ).get("name").asText();
                champKey = jsonnode.get("data").get(champ).get("key").asText();
                if (champKey.equals(Integer.toString(championId.get(timesPlayed.indexOf(Collections.max(timesPlayed)))))){
                    //Aqui nos fazemos o request para a API para descobrir qual o champ pelo ID dele.
                    break;
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return LastTenMatchesResponse.builder().summonerName(summoner.getName()).matchesPlayedLastTenDays(lastTenMatches.size()).mustPlayedChamp(champName).build();
}

    public List<HistoryMatchesResponse> getMatchDetailsBySummonerName(String summonerName){

        Summoner summoner = summonerClient.getSummonerByName(summonerName,apiKey);
        SummonerMatchListResponse summonerHistory = summonerClient.getMatchLIstByAccountID(summoner.getAccountId(),apiKey);
        List<MatchListResponse> matches = summonerHistory.getMatches();
        List<MatchResponse> listOfmatches = new ArrayList<>();
        List<MatchParticipantsIdentitiesResponse> participants = new ArrayList<>();
        HistoryMatchesResponse historyMatchesResponse;
        List<HistoryMatchesResponse> listHistoryMatchesResponses = new ArrayList<>();
        for (int i = 0; i < 10;i++){
            listOfmatches.add(summonerClient.getMatchDetails(Long.toString(matches.get(i).getGameId()),apiKey));
        }
        for (int l = 0; l < listOfmatches.size() ; l++){
        participants = listOfmatches.get(l).getParticipantIdentities();
            for (int i = 0; i < 10 ;i++){
                if(((participants.get(i).getPlayer().getSummonerName()).compareTo(summonerName)) == 0) {
                    for (int k = 0; k < 10 ;k++) {
                        if ((participants.get(i).getParticipantId()) == (listOfmatches.get(l).getParticipants().get(k).getParticipantId())) {
                            String frag = listOfmatches.get(l).getParticipants().get(k).getStats().getKills()+"/"+listOfmatches.get(l).getParticipants().get(k).getStats().getDeaths()+"/"+listOfmatches.get(l).getParticipants().get(k).getStats().getAssists();
                            //float kda = ((listOfmatches.get(l).getParticipants().get(k).getStats().getKills() + listOfmatches.get(l).getParticipants().get(k).getStats().getAssists())/listOfmatches.get(l).getParticipants().get(k).getStats().getDeaths());
                            listHistoryMatchesResponses.add(HistoryMatchesResponse.builder()
                                    .frag(frag)
                                    //.kda(kda)
                                    .champion(listOfmatches.get(l).getParticipants().get(k).getChampionId())
                                    .win(listOfmatches.get(l).getParticipants().get(k).getStats().isWin())
                                    .time((listOfmatches.get(l).getGameDuration()/60)+" min.")
                                    .visionScore(listOfmatches.get(l).getParticipants().get(k).getStats().getVisionScore())
                                    .build());
                        }
                    }
                }
            }
        }
       return listHistoryMatchesResponses;
    }

}