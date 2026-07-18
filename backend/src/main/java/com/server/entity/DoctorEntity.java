package com.server.entity;

import java.time.LocalDateTime;

import com.server.enums.DoctorStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DoctorEntity {

	// ---------------- DOCTOR ID ----------------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// ---------------- DOCTOR NAME ----------------

	private String name;

	// ---------------- DOCTOR EMAIL ----------------

	@Column(unique = true)
	private String email;

	// ---------------- DOCTOR PHONE ----------------

	@Column(unique = true)
	private String phone;

	// ---------------- DEPARTMENT ----------------

	private String department;

	// ---------------- QUALIFICATION ----------------

	private String qualification;

	// ---------------- EXPERIENCE ----------------

	private Integer experience;

	// ---------------- CONSULTATION FEE ----------------

	private Double consultationFee;

	// ---------------- PASSWORD ----------------

	private String password;

	// ---------------- STATUS ----------------

	@Enumerated(EnumType.STRING)
	private DoctorStatus status;

	// ---------------- CREATED TIME ----------------

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	// ---------------- UPDATED TIME ----------------

	@Column(nullable = false)
	private LocalDateTime updatedAt;

	// ---------------- BEFORE INSERT ----------------

	@PrePersist
	public void onCreate() {

		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();

		if (this.status == null) {
			this.status = DoctorStatus.ACTIVE;
		}
	}

	// ---------------- BEFORE UPDATE ----------------

	@PreUpdate
	public void onUpdate() {

		this.updatedAt = LocalDateTime.now();
	}

}