package com.server.model.admin.request;

import com.server.enums.AdminStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminCreateRequest {

	// ---------------- NAME ----------------

	@NotBlank(message = "Name is required")
	private String name;


	// ---------------- EMAIL ----------------

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email address")
	private String email;

	// ---------------- PHONE ----------------

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number")
	private String phone;

	// ---------------- PASSWORD ----------------

	@NotBlank(message = "Password is required")
	@Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
	private String password;

	// ---------------- STATUS ----------------

//	@NotNull(message = "Status is required")
//	private AdminStatus status;

}