package com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response;

import com.apiLOL.ApiLeagueofLegends.domain.Summoner;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchParticipantsIdentitiesResponse {
    int participantId;
    Summoner player;
}
