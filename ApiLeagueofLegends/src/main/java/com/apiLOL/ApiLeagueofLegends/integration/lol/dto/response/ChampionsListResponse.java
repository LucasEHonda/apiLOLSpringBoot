package com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ChampionsListResponse {
    String format;
    String type;
    String version;
    List<ChampionResponse> data;
}
