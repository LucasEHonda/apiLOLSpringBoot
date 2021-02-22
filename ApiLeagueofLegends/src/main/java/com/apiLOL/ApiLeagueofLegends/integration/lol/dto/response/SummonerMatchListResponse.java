package com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SummonerMatchListResponse {
    int endIndex;
    List<MatchListResponse> matches;
    int startIndex;
    int totalGames;
}