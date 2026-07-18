package com.server.configuration;

import static com.server.constants.ApiConstants.ADMIN;
import static com.server.constants.ApiConstants.AUTH;
import static com.server.constants.ApiConstants.DOCTOR;
import static com.server.constants.ApiConstants.LOGIN;
import static com.server.constants.ApiConstants.REGISTER;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.server.security.CustomUserDetailsService;
import com.server.security.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	// ==========================================================
	// DEPENDENCIES
	// ==========================================================

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	private final CustomUserDetailsService customUserDetailsService;

	// ==========================================================
	// PASSWORD ENCODER
	// ==========================================================

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}

	// ==========================================================
	// AUTHENTICATION PROVIDER
	// ==========================================================

	@Bean
	public AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsService);

		provider.setPasswordEncoder(passwordEncoder());

		return provider;

	}

	// ==========================================================
	// SECURITY FILTER CHAIN
	// ==========================================================

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http

				.cors(Customizer.withDefaults())

				.csrf(csrf -> csrf.disable())

				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				.authenticationProvider(authenticationProvider())

				.authorizeHttpRequests(auth -> auth

						// ---------------- PUBLIC ENDPOINTS ----------------

						.requestMatchers(AUTH + LOGIN, ADMIN + REGISTER).permitAll()

						// ---------------- ADMIN APIs ----------------

						.requestMatchers(ADMIN + "/**").hasRole("ADMIN")

						// ---------------- DOCTOR APIs ----------------

						.requestMatchers(DOCTOR + "/**").hasAnyRole("ADMIN", "DOCTOR")

						// ---------------- OTHER REQUESTS ----------------

						.anyRequest().authenticated())

				.formLogin(form -> form.disable())

				.httpBasic(httpBasic -> httpBasic.disable())

				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}

}