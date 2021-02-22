package com.apiLOL.ApiLeagueofLegends.api.spec;


import org.springframework.web.bind.annotation.PathVariable;

public interface SummonerController {

    public String getSummonerByName(@PathVariable("summonerName") String summonerName);

    public String getMatchlist(@PathVariable("summonerName") String summonerName);
}
