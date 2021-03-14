package com.apiLOL.ApiLeagueofLegends.api.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class MatchOnInfoResponse {

    String gameStartTime;
    String gameMode;
    String gameDuration;
    List<SummonerResponse> summoners;
}
