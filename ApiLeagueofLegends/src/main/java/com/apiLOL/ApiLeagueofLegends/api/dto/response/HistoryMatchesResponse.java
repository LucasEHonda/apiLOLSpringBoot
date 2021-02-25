package com.apiLOL.ApiLeagueofLegends.api.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HistoryMatchesResponse {
    String frag;
    String kda;
    String pKills;
    boolean win;
    int visionScore;
    String time;
    String champion;
}
