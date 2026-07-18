package com.server.model.admin.response;

import java.time.LocalDateTime;

import com.server.enums.AdminStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminResponse {

	// ---------------- ID ----------------

	private Integer id;

	// ---------------- NAME ----------------

	private String name;



	// ---------------- EMAIL ----------------

	private String email;

	// ---------------- PHONE ----------------

	private String phone;

	// ---------------- STATUS ----------------

	private AdminStatus status;

	// ---------------- CREATED AT ----------------

	private LocalDateTime createdAt;

	// ---------------- UPDATED AT ----------------

	private LocalDateTime updatedAt;

}