package com.apiLOL.ApiLeagueofLegends.domain;

import com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response.RankedInfoResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Summoner {
    String id;
    String accountId;
    String puuid;
    String name;
    int profileIconId;
    long revisionDate;
    int summonerLevel;
    String summonerName;
    List<RankedInfoResponse> rankeds;
}