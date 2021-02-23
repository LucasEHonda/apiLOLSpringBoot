package com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChampionResponse {
    String key;
    String name;
}
