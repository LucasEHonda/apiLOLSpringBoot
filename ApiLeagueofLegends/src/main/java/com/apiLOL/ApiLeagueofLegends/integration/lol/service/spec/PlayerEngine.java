package com.apiLOL.ApiLeagueofLegends.integration.lol.service.spec;

import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.MatchParticipantsResponse;

public interface PlayerEngine {
    String getFrag(MatchParticipantsResponse playerInMatch);

    String calculateKda(MatchParticipantsResponse playerInMatch);

    String calculatePKills(MatchParticipantsResponse playerInMatch, Integer globalFrag);
}
