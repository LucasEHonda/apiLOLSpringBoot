package com.apiLOL.ApiLeagueofLegends.api.impl;

import com.apiLOL.ApiLeagueofLegends.api.spec.ChampionController;
import com.apiLOL.ApiLeagueofLegends.domain.Champion;
import com.apiLOL.ApiLeagueofLegends.integration.lol.service.impl.ChampionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("champions")
public class ChampionControllerImpl implements ChampionController {
    @Autowired
    ChampionServiceImpl championService;

    @PostMapping
    public Champion newChampion(@RequestBody Champion champion) {
        return championService.newChampion(champion);
    }
}
