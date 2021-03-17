package com.apiLOL.ApiLeagueofLegends.integration.lol.service.impl;

import com.apiLOL.ApiLeagueofLegends.api.dto.response.HistoryMatchesResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.MatchParticipantsIdentitiesResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.MatchParticipantsResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.MatchResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.service.spec.ChampionService;
import com.apiLOL.ApiLeagueofLegends.integration.lol.service.spec.MatchEngine;
import com.apiLOL.ApiLeagueofLegends.integration.lol.service.spec.PlayerEngine;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MatchEngineServiceImpl implements MatchEngine {

    @Autowired
    private PlayerEngine playerEngine;

    @Autowired
    private ChampionService championService;


    @Override
    public HistoryMatchesResponse buildMatchInfo(MatchParticipantsResponse playerInMatch, MatchResponse match, List<MatchParticipantsResponse> players) {

        HashMap<String, Integer> globalFrag = getGlobalFrag(players, playerInMatch.getTeamId());

        return HistoryMatchesResponse.builder()
                .frag(playerEngine.getFrag(playerInMatch))
                .kda(playerEngine.calculateKda(playerInMatch))
                .champion(championService.getChampionName(Integer.toString(playerInMatch.getChampionId())))
                .win(playerInMatch.getStats().isWin())
                .time((match.getGameDuration()/60)+" min.")
                .visionScore(playerInMatch.getStats().getVisionScore())
                .pKills(playerEngine.calculatePKills(playerInMatch, globalFrag.get("deaths")))
                .build();
    }

    @Override
    public HashMap<String, Integer> getGlobalFrag(List<MatchParticipantsResponse> participants, int teamId) {
        HashMap<String, Integer> frag = new HashMap<>();
        int deaths = 0;
        int kills = 0;
        int assists = 0;

        participants.removeIf(p -> p.getTeamId() != teamId);
        for (MatchParticipantsResponse participant : participants) {
            deaths = deaths + participant.getStats().getDeaths();
            kills = kills + participant.getStats().getKills();
            assists = assists + participant.getStats().getAssists();
        }
        frag.put("deaths", deaths);
        frag.put("kills", kills);
        frag.put("assists", assists);
        return frag;
    }


}
