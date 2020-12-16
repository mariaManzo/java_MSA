package com.accueil.accueil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class AccueilApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccueilApplication.class, args);
	}

}
