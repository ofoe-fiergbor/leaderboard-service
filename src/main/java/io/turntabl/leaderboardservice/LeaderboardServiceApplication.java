package io.turntabl.leaderboardservice;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.info.Info;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.turntabl.leaderboardservice.service.LeaderboardRepositoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition
public class LeaderboardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaderboardServiceApplication.class, args);
	}
	@Bean
	public OpenAPI customConfiguration() {
		return new OpenAPI()
				.components(new Components())
				.info(new Info().title("Codewars leaderboard - API Doc")
						.description("Api Rest documentation."));
	}

}
