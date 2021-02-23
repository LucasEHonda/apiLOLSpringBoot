package com.apiLOL.ApiLeagueofLegends.integration.lol.service.impl;

import com.apiLOL.ApiLeagueofLegends.domain.Summoner;
import com.apiLOL.ApiLeagueofLegends.integration.lol.client.ChampionClient;
import com.apiLOL.ApiLeagueofLegends.integration.lol.client.SummonerClient;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.ChampionResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.ChampionsListResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.MatchListResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.SummonerMatchListResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.service.spec.SummonerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public String getLastTenMatchQuantity(String summonerName){
        Summoner summoner = summonerClient.getSummonerByName(summonerName,apiKey);
        SummonerMatchListResponse summonerHistory = summonerClient.getMatchLIstByAccountID(summoner.getAccountId(),apiKey);
        List<MatchListResponse> matches = summonerHistory.getMatches();

        Date dataAgora = new Date();

        long days = matches.stream().filter(match -> ((dataAgora.getTime() - match.getTimestamp()) / (1000*60*60*24)) <= 10 ).count();


        //---------------------------------------------------------------------

        List<Integer> lastTen = new ArrayList<>();
        Map<Integer, Integer> mapIdades = new TreeMap<>();
        List<Integer> valor = new ArrayList<>();
        List<Integer> championn = new ArrayList<>();


        for (int i = 0; i<matches.size();i++){
            long conta = ((dataAgora.getTime() - matches.get(i).getTimestamp()) / (1000*60*60*24));

            if (conta <= 10){
                lastTen.add(matches.get(i).getChampion());
            }
        }

        if (lastTen.size()>=1) {
            lastTen.forEach(champion -> mapIdades.compute(champion, (k, v) -> v == null ? 1 : ++v));
            for (Map.Entry<Integer, Integer> champion : mapIdades.entrySet()) {
                valor.add(champion.getValue());
                championn.add(champion.getKey());
            }

            int champMaisJogado = championn.get(valor.indexOf(Collections.max(valor)));


            String championsListResponse = championClient.getChampions();
            ObjectMapper objectMapper = new ObjectMapper();
            String champName = "";
            String champKey = "";
            try {
                JsonNode jsonnode = objectMapper.readTree(championsListResponse);
                for (Iterator<String> it = jsonnode.get("data").fieldNames(); it.hasNext();) {
                    String champ = it.next();
                    champName =jsonnode.get("data").get(champ).get("name").asText();
                    champKey = jsonnode.get("data").get(champ).get("key").asText();
                    if (champKey.equals(Integer.toString(champMaisJogado))){
                        System.out.println(champName);
                        break;
                    }

                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            System.out.println(championsListResponse);



            return ("Ola " + summoner.getName() + " nos ultimos 10 dias voce jogou " + days + " Partidas. Seu champion mais jogado foi o: "+champName);
        }

        else{
            return ("Ola " + summoner.getName() + " voce nao tem jogado mais? oq aconteceu? odeia o lol?");
        }
    }
}
