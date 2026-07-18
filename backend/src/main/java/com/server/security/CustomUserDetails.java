package com.server.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

	// ---------------- USER ID ----------------

	private Integer id;

	// ---------------- EMAIL ----------------

	private String email;

	// ---------------- PASSWORD ----------------

	private String password;

	// ---------------- ROLE ----------------

	private String role;

	// ---------------- AUTHORITIES ----------------

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return List.of(new SimpleGrantedAuthority("ROLE_" + role));

	}

	// ---------------- PASSWORD ----------------

	@Override
	public String getPassword() {

		return password;

	}

	// ---------------- USERNAME (EMAIL) ----------------

	@Override
	public String getUsername() {

		return email;

	}

	// ---------------- ACCOUNT STATUS ----------------

	@Override
	public boolean isAccountNonExpired() {

		return true;

	}

	@Override
	public boolean isAccountNonLocked() {

		return true;

	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;

	}

	@Override
	public boolean isEnabled() {

		return true;

	}

	// ---------------- GET USER ID ----------------

	public Integer getId() {

		return id;

	}

	// ---------------- GET ROLE ----------------

	public String getRole() {

		return role;

	}

}