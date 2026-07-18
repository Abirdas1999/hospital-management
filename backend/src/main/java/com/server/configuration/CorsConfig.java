package com.server.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration configuration = new CorsConfiguration();

		// ---------------- ALLOWED ORIGINS ----------------

		configuration.setAllowedOrigins(List.of("http://localhost:5173"));

		// ---------------- ALLOWED METHODS ----------------

		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));

		// ---------------- ALLOWED HEADERS ----------------

		configuration.setAllowedHeaders(List.of("*"));

		// ---------------- ALLOW CREDENTIALS ----------------

		configuration.setAllowCredentials(true);

		// ---------------- REGISTER CONFIGURATION ----------------

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		source.registerCorsConfiguration("/**", configuration);

		return source;

	}

}