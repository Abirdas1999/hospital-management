package com.server.security;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.server.util.jwt.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	// ---------------- JWT UTIL ----------------

	private final JwtUtil jwtUtil;

	// ---------------- USER DETAILS SERVICE ----------------

	private final CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {

		// ---------------- GET AUTH HEADER ----------------

		final String authHeader = request.getHeader("Authorization");

		// ---------------- CHECK HEADER ----------------

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {

			filterChain.doFilter(request, response);

			return;

		}

		// ---------------- EXTRACT TOKEN ----------------

		String token = authHeader.substring(7);

		// ---------------- EXTRACT EMAIL ----------------

		String email = jwtUtil.extractEmail(token);

		// ---------------- CHECK SECURITY CONTEXT ----------------

		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

			// ---------------- VALIDATE TOKEN ----------------

			if (jwtUtil.validateToken(token, userDetails.getUsername())) {

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);

			}

		}

		filterChain.doFilter(request, response);

	}

}