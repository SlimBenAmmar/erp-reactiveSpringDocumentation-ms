package com.odix.fr.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

public class OpenAPIConfiguration {
	
	public @Bean OpenAPI noteAPI() {
		return new OpenAPI().info(new Info().title("Note API")
				.description("A CRUD API to demonstrate Springdoc integration").version("0.0.1-SNAPSHOT")
				.license(new License().name("MIT").url("https://opensource.org/licenses/MIT")));
	}
}
