package com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response;

import com.apiLOL.ApiLeagueofLegends.domain.Champion;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChampionsListResponse {
    String format;
    String type;
    String version;
    List<Champion> data;
}
