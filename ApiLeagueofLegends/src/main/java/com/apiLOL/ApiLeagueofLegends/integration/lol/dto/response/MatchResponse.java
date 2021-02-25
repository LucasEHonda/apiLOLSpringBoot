package com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MatchResponse {
    long gameCreation;
    int gameDuration;
    long gameId;
    String gameMode;
    String gameType;
    String gameVersion;
    int mapId;
    String platformId;
    int queueId;
    int seasonId;
    List<MatchParticipantsResponse> participants;

    List<MatchParticipantsIdentitiesResponse> participantIdentities;

}
