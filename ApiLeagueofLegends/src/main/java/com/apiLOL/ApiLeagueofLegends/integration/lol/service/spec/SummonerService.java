package com.apiLOL.ApiLeagueofLegends.integration.lol.service.spec;

import com.apiLOL.ApiLeagueofLegends.api.dto.response.LastMatchesResponse;

public interface SummonerService {
    public String getNamePlusLevel(String summonerName);
    public LastMatchesResponse getLastTenMatchQuantity(String summonerName);
}
