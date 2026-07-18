package com.server.util;

import org.springframework.beans.BeanUtils;

import com.server.entity.AdminEntity;
import com.server.entity.DoctorEntity;
import com.server.model.admin.request.AdminCreateRequest;
import com.server.model.admin.response.AdminResponse;
import com.server.model.doctor.request.DoctorCreateRequest;
import com.server.model.doctor.request.DoctorUpdateRequest;
import com.server.model.doctor.response.DoctorResponse;

public class BeanCopyUtils {

	// ==========================================================
	// ADMIN
	// ==========================================================

	// ---------------- CREATE REQUEST TO ENTITY ----------------

	public static AdminEntity createRequestToEntity(AdminCreateRequest adminCreateRequest) {

		AdminEntity adminEntity = new AdminEntity();

		BeanUtils.copyProperties(adminCreateRequest, adminEntity);

		return adminEntity;
	}

	// ---------------- ENTITY TO RESPONSE ----------------

	public static AdminResponse entityToResponse(AdminEntity adminEntity) {

		AdminResponse adminResponse = new AdminResponse();

		BeanUtils.copyProperties(adminEntity, adminResponse);

		return adminResponse;
	}

	// ==========================================================
	// DOCTOR
	// ==========================================================

	// ---------------- CREATE REQUEST TO ENTITY ----------------

	public static DoctorEntity createRequestToEntity(DoctorCreateRequest doctorCreateRequest) {

		DoctorEntity doctorEntity = new DoctorEntity();

		BeanUtils.copyProperties(doctorCreateRequest, doctorEntity);

		return doctorEntity;
	}

	// ---------------- UPDATE REQUEST TO ENTITY ----------------

	public static DoctorEntity updateRequestToEntity(DoctorUpdateRequest doctorUpdateRequest) {

		DoctorEntity doctorEntity = new DoctorEntity();

		BeanUtils.copyProperties(doctorUpdateRequest, doctorEntity);

		return doctorEntity;
	}

	// ---------------- ENTITY TO RESPONSE ----------------

	public static DoctorResponse entityToResponse(DoctorEntity doctorEntity) {

		DoctorResponse doctorResponse = new DoctorResponse();

		BeanUtils.copyProperties(doctorEntity, doctorResponse);

		return doctorResponse;
	}

}