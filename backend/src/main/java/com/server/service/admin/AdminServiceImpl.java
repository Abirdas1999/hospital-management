package com.server.service.admin;

import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.dao.admin.AdminDao;
import com.server.entity.AdminEntity;
import com.server.exceptionHandler.DuplicateResourceException;
import com.server.model.admin.request.AdminCreateRequest;
import com.server.model.admin.response.AdminResponse;

import com.server.util.BeanCopyUtils;
import com.server.util.ResponseStructure;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AdminServiceImpl implements AdminService {

	// ---------------- ADMIN DAO ----------------

	private final AdminDao adminDao;

	// ---------------- PASSWORD ENCODER ----------------

	private final BCryptPasswordEncoder passwordEncoder;

	// ---------------- REGISTER ADMIN ----------------

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> registerAdmin(AdminCreateRequest adminCreateRequest) {

		// ---------------- CHECK EMAIL ----------------

		AdminEntity emailExample = new AdminEntity();
		emailExample.setEmail(adminCreateRequest.getEmail());

		if (adminDao.fetchAdmin(Example.of(emailExample)).isPresent()) {

		    throw new DuplicateResourceException("Email already exists.");

		}
		// ---------------- CHECK PHONE ----------------

		AdminEntity phoneExample = new AdminEntity();
		phoneExample.setPhone(adminCreateRequest.getPhone());

		if (adminDao.fetchAdmin(Example.of(phoneExample)).isPresent()) {

			throw new DuplicateResourceException("Phone number already exists.");

		}

		// ---------------- REQUEST TO ENTITY ----------------

		AdminEntity adminEntity = BeanCopyUtils.createRequestToEntity(adminCreateRequest);

		// ---------------- ENCODE PASSWORD ----------------

		adminEntity.setPassword(passwordEncoder.encode(adminCreateRequest.getPassword()));

		// ---------------- SAVE ADMIN ----------------

		adminEntity = adminDao.saveAdmin(adminEntity);

		// ---------------- ENTITY TO RESPONSE ----------------

		AdminResponse adminResponse = BeanCopyUtils.entityToResponse(adminEntity);

		// ---------------- RESPONSE ----------------

		ResponseStructure<AdminResponse> responseStructure = new ResponseStructure<>();

		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Admin Registered Successfully");
		responseStructure.setData(adminResponse);

		return ResponseEntity.status(HttpStatus.CREATED).body(responseStructure);
	}

}