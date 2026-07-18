package com.server.model.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequest {

	// ---------------- OLD PASSWORD ----------------

	@NotBlank(message = "Old Password is required")
	private String oldPassword;

	// ---------------- NEW PASSWORD ----------------

	@NotBlank(message = "New Password is required")
	private String newPassword;

}