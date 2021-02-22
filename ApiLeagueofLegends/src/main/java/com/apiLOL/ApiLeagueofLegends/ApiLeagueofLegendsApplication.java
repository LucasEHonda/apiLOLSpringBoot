package com.apiLOL.ApiLeagueofLegends;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiLeagueofLegendsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiLeagueofLegendsApplication.class, args);
	}

}
