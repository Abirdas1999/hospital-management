package com.server.service.auth;

import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.dao.admin.AdminDao;
import com.server.dao.doctor.DoctorDao;
import com.server.entity.AdminEntity;
import com.server.entity.DoctorEntity;
import com.server.exceptionHandler.InvalidCredentialsException;
import com.server.model.auth.request.ChangePasswordRequest;
import com.server.model.auth.request.LoginRequest;
import com.server.model.auth.response.LoginResponse;

import com.server.util.ResponseStructure;
import com.server.util.jwt.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AuthenticationServiceImpl implements AuthenticationService {

	// ---------------- ADMIN DAO ----------------

	private final AdminDao adminDao;

	// ---------------- DOCTOR DAO ----------------

	private final DoctorDao doctorDao;

	// ---------------- PASSWORD ENCODER ----------------

	private final BCryptPasswordEncoder passwordEncoder;

	// ---------------- JWT UTIL ----------------

	private final JwtUtil jwtUtil;

	// ==========================================================
	// LOGIN
	// ==========================================================

	@Override
	public ResponseEntity<ResponseStructure<LoginResponse>> login(LoginRequest loginRequest) {

		// ---------------- CHECK ADMIN ----------------

		AdminEntity adminExample = new AdminEntity();
		adminExample.setEmail(loginRequest.getEmail());

		AdminEntity admin = adminDao.fetchAdmin(Example.of(adminExample)).orElse(null);

		if (admin != null) {

			if (!passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword())) {

				throw new InvalidCredentialsException("Invalid Email or Password");

			}

			String token = jwtUtil.generateToken(admin.getId(), admin.getEmail(), "ADMIN");

			LoginResponse loginResponse = new LoginResponse(admin.getId(), admin.getName(), "ADMIN", token);

			ResponseStructure<LoginResponse> response = new ResponseStructure<>();

			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Login Successful");
			response.setData(loginResponse);

			return ResponseEntity.ok(response);

		}

		// ---------------- CHECK DOCTOR ----------------

		DoctorEntity doctor = doctorDao.fetchByEmail(loginRequest.getEmail()).orElse(null);

		if (doctor != null) {

			if (!passwordEncoder.matches(loginRequest.getPassword(), doctor.getPassword())) {

				throw new InvalidCredentialsException("Invalid Email or Password");

			}

			String token = jwtUtil.generateToken(doctor.getId(), doctor.getEmail(), "DOCTOR");

			LoginResponse loginResponse = new LoginResponse(doctor.getId(), doctor.getName(), "DOCTOR", token);

			ResponseStructure<LoginResponse> response = new ResponseStructure<>();

			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Login Successful");
			response.setData(loginResponse);

			return ResponseEntity.ok(response);

		}

		// ---------------- INVALID LOGIN ----------------

		throw new InvalidCredentialsException("Invalid Email or Password");

	}

	// ==========================================================
	// CHANGE PASSWORD
	// ==========================================================

	@Override
	public ResponseEntity<ResponseStructure<String>> changePassword(ChangePasswordRequest changePasswordRequest,
			HttpServletRequest request) {

		// ---------------- GET AUTHORIZATION HEADER ----------------

		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {

			throw new InvalidCredentialsException("Invalid Token");

		}

		// ---------------- EXTRACT TOKEN ----------------

		String token = authHeader.substring(7);

		// ---------------- EXTRACT EMAIL ----------------

		String email = jwtUtil.extractEmail(token);

		// ---------------- CHECK ADMIN ----------------

		AdminEntity adminExample = new AdminEntity();
		adminExample.setEmail(email);

		AdminEntity admin = adminDao.fetchAdmin(Example.of(adminExample)).orElse(null);

		if (admin != null) {

			if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), admin.getPassword())) {

				throw new InvalidCredentialsException("Old Password is Incorrect");

			}

			admin.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));

			adminDao.saveAdmin(admin);

			ResponseStructure<String> response = new ResponseStructure<>();

			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Password Changed Successfully");
			response.setData("Admin Password Updated Successfully");

			return ResponseEntity.ok(response);

		}

		// ---------------- CHECK DOCTOR ----------------

		DoctorEntity doctor = doctorDao.fetchByEmail(email).orElse(null);

		if (doctor != null) {

			if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), doctor.getPassword())) {

				throw new InvalidCredentialsException("Old Password is Incorrect");

			}

			doctor.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));

			doctorDao.saveDoctor(doctor);

			ResponseStructure<String> response = new ResponseStructure<>();

			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Password Changed Successfully");
			response.setData("Doctor Password Updated Successfully");

			return ResponseEntity.ok(response);

		}

		// ---------------- USER NOT FOUND ----------------

		throw new InvalidCredentialsException("User Not Found");

	}

	// ==========================================================
	// LOGOUT
	// ==========================================================

	@Override
	public ResponseEntity<ResponseStructure<String>> logout() {

		ResponseStructure<String> response = new ResponseStructure<>();

		response.setStatus(HttpStatus.OK.value());
		response.setMessage("Logout Successful");
		response.setData("User Logged Out Successfully");

		return ResponseEntity.ok(response);

	}

}