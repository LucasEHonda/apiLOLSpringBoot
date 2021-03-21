package com.apiLOL.ApiLeagueofLegends.integration.lol.service.spec;

import com.apiLOL.ApiLeagueofLegends.api.dto.response.HistoryMatchesResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.MatchParticipantsResponse;
import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.MatchResponse;

import java.util.HashMap;
import java.util.List;

public interface MatchEngine {
    HistoryMatchesResponse buildMatchInfo(MatchParticipantsResponse playerInMatch, MatchResponse match, List<MatchParticipantsResponse> players);

    HashMap<String, Integer> getGlobalFrag(List<MatchParticipantsResponse> participants, int teamId);
}
