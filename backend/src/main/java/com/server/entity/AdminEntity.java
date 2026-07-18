package com.server.entity;

import java.time.LocalDateTime;

import com.server.enums.AdminStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admin")
public class AdminEntity {

	// ---------------- ID ----------------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// ---------------- NAME ----------------

	@Column(nullable = false)
	private String name;



	// ---------------- EMAIL ----------------

	@Column(nullable = false, unique = true)
	private String email;

	// ---------------- PHONE ----------------

	@Column(nullable = false, unique = true)
	private String phone;

	// ---------------- PASSWORD ----------------

	@Column(nullable = false)
	private String password;

	// ---------------- STATUS ----------------

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AdminStatus status;

	// ---------------- CREATED AT ----------------

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	// ---------------- UPDATED AT ----------------

	@Column(nullable = false)
	private LocalDateTime updatedAt;

	// ---------------- PRE PERSIST ----------------

	@PrePersist
	public void prePersist() {

		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();

		if (status == null) {
			status = AdminStatus.ACTIVE;
		}
	}

	// ---------------- PRE UPDATE ----------------

	@PreUpdate
	public void preUpdate() {

		updatedAt = LocalDateTime.now();
	}

}