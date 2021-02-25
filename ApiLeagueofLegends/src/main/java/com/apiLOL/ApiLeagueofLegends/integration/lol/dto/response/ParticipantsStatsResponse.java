package com.apiLOL.ApiLeagueofLegends.integration.lol.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParticipantsStatsResponse {
    int assists;
    int damageDealtToObjectives;
    int damageDealtToTurrets;
    int damageSelfMitigated;
    int deaths;
    int doubleKills;
    int goldEarned;
    int goldSpent;
    int inibitorKills;
    int item0;
    int item1;
    int item2;
    int item3;
    int item4;
    int item5;
    int item6;
    int killingSprees;
    int Kills;
    int neutralMinionsKilled;
    int neutralMinionsKilledEnemyJungle;
    int neutralMinionsKilledTeamJungle;
    int participantId;
    int pentaKills;
    int physicalDamageDealt;
    int physicalDamageDealtToChampions;
    int physicalDamageTaken;
    int quadraKills;
    int timeCCingOthers;
    int totalDamageDealt;
    int totalDamageDealtToChampions;
    int totalDamageTaken;
    int totalHeal;
    int totalMinionsKilled;
    int tripleKills;
    int turretKills;
    int visionScore;
    int visionWardsBoughtInGame;
    int wardsKilled;
    int wardsPlaced;
    boolean win;
}
