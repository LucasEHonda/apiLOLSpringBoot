package com.apiLOL.ApiLeagueofLegends.integration.lol.service.impl;

import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.MatchParticipantsResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.ParticipantsStatsResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.service.spec.PlayerEngine;

import java.text.DecimalFormat;

public class PlayerEngineImpl implements PlayerEngine {
    @Override
    public String getFrag(MatchParticipantsResponse playerInMatch) {
        StringBuilder playerFragSB = new StringBuilder();

        ParticipantsStatsResponse stats = playerInMatch.getStats();

        return playerFragSB
                .append(stats.getKills())
                .append("/")
                .append(stats.getDeaths())
                .append("/")
                .append(stats.getAssists()).toString();

    }

    @Override
    public String calculateKda(MatchParticipantsResponse playerInMatch) {
        ParticipantsStatsResponse stats = playerInMatch.getStats();

        if(stats.getDeaths() == 0){
            return Integer.toString(stats.getKills() + stats.getAssists());
        }else{
            return new DecimalFormat("#0.##").format( ( stats.getKills() +  stats.getAssists())/ stats.getDeaths() );
        }
    }

    @Override
    public String calculatePKills(MatchParticipantsResponse playerInMatch, Integer globalFrag) {
        ParticipantsStatsResponse stats = playerInMatch.getStats();
        if(globalFrag == 0){
            return "0%";
        }else{
            return ((stats.getKills() + stats.getAssists())*100)/globalFrag + "%";
        }
    }
}
