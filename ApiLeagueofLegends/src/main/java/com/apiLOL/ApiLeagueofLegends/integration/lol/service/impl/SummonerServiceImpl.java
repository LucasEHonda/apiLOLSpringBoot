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

import java.text.DecimalFormat;
import java.util.*;

@Service
public class SummonerServiceImpl implements SummonerService {
    @Value("${api-lol.api-key}")
    String apiKey;


    @Autowired
    SummonerClient summonerClient;

    @Autowired
    ChampionClient championClient;


    //String championsListResponse = championClient.getChampions();

    @Override
    public String getNamePlusLevel(String summonerName) {
        Summoner summoner = summonerClient.getSummonerByName(summonerName,apiKey);
        return (summoner.getName() + " " + "Nivel: " + summoner.getSummonerLevel());
    }

    public LastTenMatchesResponse getLastTenMatchQuantity(String summonerName){

        Summoner summoner = summonerClient.getSummonerByName(summonerName,apiKey);
        String championsListResponse = championClient.getChampions();
        SummonerMatchListResponse summonerHistory = summonerClient.getMatchLIstByAccountID(summoner.getAccountId(),apiKey);
        List<MatchListResponse> matches = summonerHistory.getMatches();
        List<Integer> lastTenMatches = new ArrayList<>();
        Map<Integer, Integer> champCount = new TreeMap<>();
        List<Integer> timesPlayed = new ArrayList<>();
        List<Integer> championId = new ArrayList<>();
        Date dataAgora = new Date();

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

        String champName = getChampionName(championId.get(timesPlayed.indexOf(Collections.max(timesPlayed))),championsListResponse);

        return LastTenMatchesResponse.builder().summonerName(summoner.getName()).matchesPlayedLastTenDays(lastTenMatches.size()).mustPlayedChamp(champName).build();
}

    public List<HistoryMatchesResponse> getMatchDetailsBySummonerName(String summonerName){
        String championsListResponse = championClient.getChampions();
        Summoner summoner = summonerClient.getSummonerByName(summonerName,apiKey);
        List<MatchListResponse> matches = summonerClient.getMatchLIstByAccountID(summoner.getAccountId(),apiKey).getMatches();
        List<HistoryMatchesResponse> listHistoryMatchesResponses = new ArrayList<>();
        //o for abaixo seria melhor com um foreach
        for (int l = 0; l < 10 ; l++){
            MatchResponse match = summonerClient.getMatchDetails(Long.toString(matches.get(l).getGameId()),apiKey);
            List<MatchParticipantsIdentitiesResponse> participants = match.getParticipantIdentities();
            int idxParticipantId = getIdxOfParticipant(participants, summoner);
            listHistoryMatchesResponses.add(HistoryMatchesResponse.builder()
                    .frag(getFrag(match.getParticipants().get(idxParticipantId).getStats().getKills(),match.getParticipants().get(idxParticipantId).getStats().getAssists(),match.getParticipants().get(idxParticipantId).getStats().getDeaths()))
                    .kda(calculateKDA(match.getParticipants().get(idxParticipantId).getStats().getKills(), match.getParticipants().get(idxParticipantId).getStats().getAssists(), match.getParticipants().get(idxParticipantId).getStats().getDeaths()))
                    .champion(getChampionName(match.getParticipants().get(idxParticipantId).getChampionId(), championsListResponse))
                    .win(match.getParticipants().get(idxParticipantId).getStats().isWin())
                    .time((match.getGameDuration()/60)+" min.")
                    .visionScore(match.getParticipants().get(idxParticipantId).getStats().getVisionScore())
                    .pKills(calculatePKills(match.getParticipants().get(idxParticipantId).getStats().getKills(), match.getParticipants().get(idxParticipantId).getStats().getAssists(),(getGlobalFrag( match.getParticipants(), match.getParticipants().get(idxParticipantId).getTeamId())).get(0)))
                    .build());
        }
       return listHistoryMatchesResponses;
    }

    private String getFrag(int kills, int assists, int deaths){
        return kills+"/"+deaths+"/"+assists;
    }

    private String calculatePKills(int kills, int assists,int globalFrag){
        return ((kills + assists)*100)/globalFrag + "%";
    }

    private String calculateKDA(int kills, int assists, int deaths){
        return new DecimalFormat("#0.##").format( ( (float) kills +  (float) assists)/ (float) deaths );
    }
    private String getChampionName(int champId,String championsListResponse){
        ObjectMapper objectMapper = new ObjectMapper();
        String champName = "";
        String champKey = "";


        try {
            JsonNode jsonnode = objectMapper.readTree(championsListResponse);
            for (Iterator<String> it = jsonnode.get("data").fieldNames(); it.hasNext();) {
                String champ = it.next();
                champName =jsonnode.get("data").get(champ).get("name").asText();
                champKey = jsonnode.get("data").get(champ).get("key").asText();
                if (champKey.equals(Integer.toString(champId))){
                    //Aqui nos fazemos o request para a API para descobrir qual o champ pelo ID dele.
                    break;
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return champName;
    }


    private List<Integer> getGlobalFrag(List<MatchParticipantsResponse> participants, int teamId){
        List<Integer> frag = new ArrayList<>();
        int deaths = 0;
        int kills = 0;
        int assists = 0;

        for (int i = 0;i< participants.size();i++){
            if(participants.get(i).getTeamId() == teamId){
                deaths = deaths + participants.get(i).getStats().getDeaths();
                kills = kills + participants.get(i).getStats().getKills();
                assists = assists + participants.get(i).getStats().getAssists();
            }
        }
        frag.add(kills);
        frag.add(deaths);
        frag.add(assists);
        return frag;
    }

    private int getIdxOfParticipant(List<MatchParticipantsIdentitiesResponse> participants, Summoner summoner){
        int aux=-1,i;
        for(i = 0; i < 10 ;i++){
            if(((participants.get(i).getPlayer().getSummonerName()).compareTo(summoner.getName())) == 0) {
                aux = i;
            }
        }
        return aux;
    }
}