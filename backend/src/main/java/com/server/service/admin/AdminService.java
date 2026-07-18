package com.server.service.admin;

import org.springframework.http.ResponseEntity;

import com.server.model.admin.request.AdminCreateRequest;
import com.server.model.admin.response.AdminResponse;
import com.server.util.ResponseStructure;

public interface AdminService {

	// ---------------- REGISTER ADMIN ----------------

	ResponseEntity<ResponseStructure<AdminResponse>> registerAdmin(
			AdminCreateRequest adminCreateRequest);

}