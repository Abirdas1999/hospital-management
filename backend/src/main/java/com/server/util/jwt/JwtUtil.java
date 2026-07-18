package com.server.util.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	// ---------------- JWT SECRET ----------------

	@Value("${jwt.secret}")
	private String secret;

	// ---------------- JWT EXPIRATION ----------------

	@Value("${jwt.expiration}")
	private Long expiration;

	// ---------------- SECRET KEY ----------------

	private SecretKey getSecretKey() {

		return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

	}

	// ==========================================================
	// GENERATE TOKEN
	// ==========================================================

	public String generateToken(Integer id, String email, String role) {

		Map<String, Object> claims = new HashMap<>();

		claims.put("id", id);
		claims.put("role", role);

		return Jwts.builder()
				.claims(claims)
				.subject(email)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(getSecretKey())
				.compact();

	}

	// ==========================================================
	// EXTRACT EMAIL
	// ==========================================================

	public String extractEmail(String token) {

		return extractClaim(token, Claims::getSubject);

	}

	// ==========================================================
	// EXTRACT ROLE
	// ==========================================================

	public String extractRole(String token) {

		return extractAllClaims(token).get("role", String.class);

	}

	// ==========================================================
	// EXTRACT USER ID
	// ==========================================================

	public Integer extractUserId(String token) {

		return extractAllClaims(token).get("id", Integer.class);

	}

	// ==========================================================
	// EXTRACT CLAIM
	// ==========================================================

	public <T> T extractClaim(
			String token,
			Function<Claims, T> claimsResolver) {

		final Claims claims = extractAllClaims(token);

		return claimsResolver.apply(claims);

	}

	// ==========================================================
	// EXTRACT ALL CLAIMS
	// ==========================================================

	private Claims extractAllClaims(String token) {

		return Jwts.parser()
				.verifyWith(getSecretKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();

	}

	// ==========================================================
	// TOKEN EXPIRED
	// ==========================================================

	private boolean isTokenExpired(String token) {

		return extractClaim(token, Claims::getExpiration)
				.before(new Date());

	}

	// ==========================================================
	// VALIDATE TOKEN
	// ==========================================================

	public boolean validateToken(String token, String email) {

		return extractEmail(token).equals(email)
				&& !isTokenExpired(token);

	}

}