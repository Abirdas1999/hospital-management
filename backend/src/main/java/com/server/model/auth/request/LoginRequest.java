package com.server.model.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

	// ---------------- USERNAME ----------------

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email")
	private String email;

	// ---------------- PASSWORD ----------------

	@NotBlank(message = "Password is required")
	private String password;

}