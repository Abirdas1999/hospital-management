package com.server.model.doctor.request;

import com.server.enums.DoctorStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorUpdateRequest {

	// ---------------- DOCTOR NAME ----------------

	@NotBlank(message = "Doctor name is required")
	private String name;

	// ---------------- EMAIL ----------------

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email address")
	private String email;

	// ---------------- PHONE ----------------

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number")
	private String phone;

	// ---------------- DEPARTMENT ----------------

	@NotBlank(message = "Department is required")
	private String department;

	// ---------------- QUALIFICATION ----------------

	@NotBlank(message = "Qualification is required")
	private String qualification;

	// ---------------- EXPERIENCE ----------------

	@NotNull(message = "Experience is required")
	@PositiveOrZero(message = "Experience cannot be negative")
	private Integer experience;

	// ---------------- CONSULTATION FEE ----------------

	@NotNull(message = "Consultation fee is required")
	@Positive(message = "Consultation fee must be greater than 0")
	private Double consultationFee;

	// ---------------- PASSWORD ----------------

//	@NotBlank(message = "Password is required")
//	@Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
//	private String password;

	// ---------------- STATUS ----------------


	@NotNull(message = "Status is required")
	private DoctorStatus status;

}