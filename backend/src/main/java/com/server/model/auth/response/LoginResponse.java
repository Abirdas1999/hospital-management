package com.server.model.auth.response;

import com.server.dao.admin.AdminDao;
import com.server.dao.doctor.DoctorDao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class LoginResponse {

	// ---------------- USER ID ----------------

	private Integer id;

	// ---------------- NAME ----------------

	private String name;

	// ---------------- ROLE ----------------

	private String role;

	// ---------------- JWT TOKEN ----------------

	private String token;

}