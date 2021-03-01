package com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Champion")
@Builder
public class ChampionResponse {
    @Id
    String key;
    String name;
}
