package com.server.model.doctor.response;

import java.time.LocalDateTime;

import com.server.enums.DoctorStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorResponse {

	// ---------------- DOCTOR ID ----------------

	private Integer id;

	// ---------------- DOCTOR NAME ----------------

	private String name;

	// ---------------- DOCTOR EMAIL ----------------

	private String email;

	// ---------------- DOCTOR PHONE ----------------

	private String phone;

	// ---------------- DEPARTMENT ----------------

	private String department;

	// ---------------- QUALIFICATION ----------------

	private String qualification;

	// ---------------- EXPERIENCE ----------------

	private Integer experience;

	// ---------------- CONSULTATION FEE ----------------

	private Double consultationFee;

	// ---------------- ACCOUNT STATUS ----------------

	private DoctorStatus status;

	// ---------------- CREATED DATE & TIME ----------------

	private LocalDateTime createdAt;

	// ---------------- UPDATED DATE & TIME ----------------

	private LocalDateTime updatedAt;

}