package com.apiLOL.ApiLeagueofLegends.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Champion")
@Builder
public class Champion {
    @Id
    String id;
    @Indexed
    String key;
    String name;
}
