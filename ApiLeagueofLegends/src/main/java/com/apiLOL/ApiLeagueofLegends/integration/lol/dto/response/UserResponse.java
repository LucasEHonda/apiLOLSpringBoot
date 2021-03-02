package com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("User")
@Builder
public class UserResponse {
    @Id
    String userId;
    String userName;
    String userSummonerName;
}
