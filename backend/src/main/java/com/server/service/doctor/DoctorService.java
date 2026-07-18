package com.server.service.doctor;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.server.model.doctor.request.DoctorCreateRequest;
import com.server.model.doctor.request.DoctorPasswordRequest;
import com.server.model.doctor.request.DoctorUpdateRequest;
import com.server.model.doctor.response.DoctorResponse;
import com.server.util.ResponseStructure;

public interface DoctorService {

	// ---------------- SAVE DOCTOR ----------------

	ResponseEntity<ResponseStructure<DoctorResponse>> saveDoctor(DoctorCreateRequest doctorCreateRequest);

	// ---------------- FETCH ALL DOCTORS ----------------

	ResponseEntity<ResponseStructure<Page<DoctorResponse>>> fetchAllDoctors(Integer page, Integer size, String sortBy,
			String direction);

	// ---------------- FETCH DOCTOR BY ID ----------------

	ResponseEntity<ResponseStructure<DoctorResponse>> fetchDoctorById(Integer id);

	// ---------------- UPDATE DOCTOR ----------------

	ResponseEntity<ResponseStructure<DoctorResponse>> updateDoctor(Integer id, DoctorUpdateRequest doctorUpdateRequest);

	// ---------------- RESET DOCTOR PASSWORD ----------------

	ResponseEntity<ResponseStructure<String>> resetDoctorPassword(Integer id,
			DoctorPasswordRequest doctorPasswordRequest);

	// ---------------- DELETE DOCTOR ----------------

	ResponseEntity<ResponseStructure<String>> deleteDoctor(Integer id);
	// ---------------- SEARCH DOCTORS ----------------

	ResponseEntity<ResponseStructure<Page<DoctorResponse>>> searchDoctors(

			String name,

			String email,

			String phone,

			String department,

			String qualification,

			Integer page,

			Integer size,

			String sortBy,

			String direction);

}