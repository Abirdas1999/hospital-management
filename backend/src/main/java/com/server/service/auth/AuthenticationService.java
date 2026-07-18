package com.server.service.auth;

import org.springframework.http.ResponseEntity;

import com.server.model.auth.request.ChangePasswordRequest;
import com.server.model.auth.request.LoginRequest;
import com.server.model.auth.response.LoginResponse;
import com.server.util.ResponseStructure;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {

	// ---------------- LOGIN ----------------

	ResponseEntity<ResponseStructure<LoginResponse>> login(LoginRequest loginRequest);

	// ---------------- CHANGE PASSWORD ----------------

	ResponseEntity<ResponseStructure<String>> changePassword(ChangePasswordRequest changePasswordRequest,
			HttpServletRequest request);
	
	// ---------------- LOGOUT ----------------

	ResponseEntity<ResponseStructure<String>> logout();
}