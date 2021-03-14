package com.apiLOL.ApiLeagueofLegends.api.dto.response;

import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.RankedInfoResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SummonerResponse {

    String summonerName;
    String championName;
    List<RankedInfoResponse> rankedInfos;
    //String spell1;
    //String spell2;
    String team;
}
