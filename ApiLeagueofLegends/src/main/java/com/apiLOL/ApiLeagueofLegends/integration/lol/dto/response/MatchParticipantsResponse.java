package com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MatchParticipantsResponse {
    int championId;
    int participantId;
    int spell1Id;
    int spell2Id;
    ParticipantsStatsResponse stats;
    int teamId;
}
