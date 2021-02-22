package com.apiLOL.ApiLeagueofLegends.domain;

import lombok.Builder;
import lombok.Data;

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
}