package com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RankedInfoResponse {
    Boolean freshBlood;
    Boolean hotStreak;
    Boolean inactive;
    int leaguePoints;
    int losses;
    String queueType;
    String rank;
    String tier;
    int wins;

}
