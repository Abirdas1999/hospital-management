package com.server.controller.admin;

import static com.server.constants.ApiConstants.ADMIN;
import static com.server.constants.ApiConstants.REGISTER;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.model.admin.request.AdminCreateRequest;
import com.server.model.admin.response.AdminResponse;
import com.server.service.admin.AdminService;
import com.server.util.ResponseStructure;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ADMIN)
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

	// ---------------- ADMIN SERVICE ----------------

	private final AdminService adminService;

	// ---------------- REGISTER ADMIN ----------------

	@PostMapping(REGISTER)
	public ResponseEntity<ResponseStructure<AdminResponse>> registerAdmin(
			@Valid @RequestBody AdminCreateRequest adminCreateRequest) {

		return adminService.registerAdmin(adminCreateRequest);

	}

}