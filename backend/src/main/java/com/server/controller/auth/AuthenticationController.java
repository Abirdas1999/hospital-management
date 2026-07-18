package com.server.controller.auth;

import static com.server.constants.ApiConstants.AUTH;
import static com.server.constants.ApiConstants.CHANGE_PASSWORD;
import static com.server.constants.ApiConstants.LOGIN;
import static com.server.constants.ApiConstants.LOGOUT;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.server.model.auth.request.ChangePasswordRequest;
import com.server.model.auth.request.LoginRequest;
import com.server.model.auth.response.LoginResponse;
import com.server.service.auth.AuthenticationService;
import com.server.util.ResponseStructure;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {

	// ---------------- AUTHENTICATION SERVICE ----------------

	private final AuthenticationService authenticationService;

	// ---------------- LOGIN ----------------

	@PostMapping(LOGIN)
	public ResponseEntity<ResponseStructure<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {

		return authenticationService.login(loginRequest);

	}

	// ---------------- CHANGE PASSWORD ----------------

	@PutMapping(CHANGE_PASSWORD)
	public ResponseEntity<ResponseStructure<String>> changePassword(
			@Valid @RequestBody ChangePasswordRequest changePasswordRequest, HttpServletRequest request) {

		return authenticationService.changePassword(changePasswordRequest, request);

	}

	// ---------------- LOGOUT ----------------

	@PostMapping(LOGOUT)
	public ResponseEntity<ResponseStructure<String>> logout() {

		return authenticationService.logout();

	}

}