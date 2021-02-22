package com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchListResponse {
    int champion;
    long gameId;
    String lane;
    String platformId;
    int queue;
    String role;
    int season;
    long timestamp;
}