package com.server.controller.doctor;

import static com.server.constants.ApiConstants.DELETE;
import static com.server.constants.ApiConstants.DOCTOR;
import static com.server.constants.ApiConstants.FETCH;
import static com.server.constants.ApiConstants.RESET_PASSWORD;
import static com.server.constants.ApiConstants.SAVE;
import static com.server.constants.ApiConstants.SEARCH;
import static com.server.constants.ApiConstants.UPDATE;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.server.model.doctor.request.DoctorCreateRequest;
import com.server.model.doctor.request.DoctorPasswordRequest;
import com.server.model.doctor.request.DoctorUpdateRequest;
import com.server.model.doctor.response.DoctorResponse;
import com.server.service.doctor.DoctorService;
import com.server.util.ResponseStructure;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(DOCTOR)
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "http://localhost:5173")
public class DoctorController {

	// ---------------- DOCTOR SERVICE ----------------

	private final DoctorService doctorService;

	// ---------------- SAVE DOCTOR ----------------

	@PostMapping(SAVE)
	public ResponseEntity<ResponseStructure<DoctorResponse>> saveDoctor(
			@Valid @RequestBody DoctorCreateRequest doctorCreateRequest) {

		return doctorService.saveDoctor(doctorCreateRequest);

	}

	// ---------------- FETCH ALL DOCTORS ----------------

	@GetMapping(FETCH)
	public ResponseEntity<ResponseStructure<Page<DoctorResponse>>> fetchAllDoctors(

			@RequestParam(defaultValue = "0") Integer page,

			@RequestParam(defaultValue = "5") Integer size,

			@RequestParam(defaultValue = "id") String sortBy,

			@RequestParam(defaultValue = "asc") String direction) {

		return doctorService.fetchAllDoctors(page, size, sortBy, direction);

	}

	// ---------------- SEARCH DOCTORS ----------------

	@GetMapping(SEARCH)
	public ResponseEntity<ResponseStructure<Page<DoctorResponse>>> searchDoctors(

			@RequestParam(required = false) String name,

			@RequestParam(required = false) String email,

			@RequestParam(required = false) String phone,

			@RequestParam(required = false) String department,

			@RequestParam(required = false) String qualification,

			@RequestParam(defaultValue = "0") Integer page,

			@RequestParam(defaultValue = "5") Integer size,

			@RequestParam(defaultValue = "id") String sortBy,

			@RequestParam(defaultValue = "asc") String direction) {

		return doctorService.searchDoctors(name, email, phone, department, qualification, page, size, sortBy,
				direction);

	}

	// ---------------- FETCH DOCTOR BY ID ----------------

	@GetMapping(FETCH + "/{id}")
	public ResponseEntity<ResponseStructure<DoctorResponse>> fetchDoctorById(@PathVariable Integer id) {

		return doctorService.fetchDoctorById(id);

	}

	// ---------------- UPDATE DOCTOR ----------------

	@PutMapping(UPDATE + "/{id}")
	public ResponseEntity<ResponseStructure<DoctorResponse>> updateDoctor(@PathVariable Integer id,
			@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest) {

		return doctorService.updateDoctor(id, doctorUpdateRequest);

	}

	// ---------------- RESET DOCTOR PASSWORD ----------------

	@PutMapping(RESET_PASSWORD + "/{id}")
	public ResponseEntity<ResponseStructure<String>> resetDoctorPassword(@PathVariable Integer id,
			@Valid @RequestBody DoctorPasswordRequest doctorPasswordRequest) {

		return doctorService.resetDoctorPassword(id, doctorPasswordRequest);

	}

	// ---------------- DELETE DOCTOR ----------------

	@DeleteMapping(DELETE + "/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteDoctor(@PathVariable Integer id) {

		return doctorService.deleteDoctor(id);

	}

}