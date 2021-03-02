package com.apiLOL.ApiLeagueofLegends.api.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LastTenMatchesResponse {
    String summonerName;
    int matchesPlayedLastTenDays;
    String mustPlayedChamp;
    String averageTimeSpent;
}
