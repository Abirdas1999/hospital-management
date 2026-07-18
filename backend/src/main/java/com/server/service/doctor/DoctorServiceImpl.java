package com.server.service.doctor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.dao.doctor.DoctorDao;
import com.server.entity.DoctorEntity;
import com.server.exceptionHandler.DuplicateResourceException;
import com.server.exceptionHandler.ResourceNotFoundException;
import com.server.model.doctor.request.DoctorCreateRequest;
import com.server.model.doctor.request.DoctorPasswordRequest;
import com.server.model.doctor.request.DoctorUpdateRequest;
import com.server.model.doctor.response.DoctorResponse;

import com.server.util.BeanCopyUtils;
import com.server.util.ResponseStructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.server.enums.DoctorStatus;

import org.springframework.data.domain.ExampleMatcher;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

	// ---------------- LOGGER ----------------

	private static final Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

	// ---------------- DOCTOR DAO ----------------

	private final DoctorDao doctorDao;

	// ---------------- PASSWORD ENCODER ----------------

	private final BCryptPasswordEncoder passwordEncoder;

	// ---------------- SAVE DOCTOR ----------------

	@Override
	public ResponseEntity<ResponseStructure<DoctorResponse>> saveDoctor(DoctorCreateRequest doctorCreateRequest) {

		logger.info("Creating doctor with email : {}", doctorCreateRequest.getEmail());

		// ---------- CHECK DUPLICATE EMAIL ----------

		DoctorEntity emailDoctor = new DoctorEntity();
		emailDoctor.setEmail(doctorCreateRequest.getEmail());

		if (doctorDao.fetchDoctor(Example.of(emailDoctor)).isPresent()) {

			logger.warn("Duplicate doctor email : {}", doctorCreateRequest.getEmail());

			throw new DuplicateResourceException("Doctor email already exists.");
		}

		// ---------- CHECK DUPLICATE PHONE ----------

		DoctorEntity phoneDoctor = new DoctorEntity();
		phoneDoctor.setPhone(doctorCreateRequest.getPhone());

		if (doctorDao.fetchDoctor(Example.of(phoneDoctor)).isPresent()) {

			logger.warn("Duplicate doctor phone : {}", doctorCreateRequest.getPhone());

			throw new DuplicateResourceException("Doctor phone number already exists.");
		}

		// ---------- REQUEST TO ENTITY ----------

		DoctorEntity doctorEntity = BeanCopyUtils.createRequestToEntity(doctorCreateRequest);

		// ---------- DEFAULT STATUS ----------

//		doctorEntity.setStatus(DoctorStatus.ACTIVE);

		// ---------- ENCRYPT PASSWORD ----------

		doctorEntity.setPassword(passwordEncoder.encode(doctorCreateRequest.getPassword()));

		// ---------- SAVE ----------

		doctorEntity = doctorDao.saveDoctor(doctorEntity);

		logger.info("Doctor registered successfully. ID : {}", doctorEntity.getId());

		// ---------- ENTITY TO RESPONSE ----------

		DoctorResponse doctorResponse = BeanCopyUtils.entityToResponse(doctorEntity);

		// ---------- RESPONSE ----------

		ResponseStructure<DoctorResponse> response = new ResponseStructure<>();

		response.setStatus(HttpStatus.CREATED.value());
		response.setMessage("Doctor Registered Successfully");
		response.setData(doctorResponse);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	// ---------------- FETCH ALL DOCTORS ----------------

	@Override
	public ResponseEntity<ResponseStructure<Page<DoctorResponse>>> fetchAllDoctors(Integer page, Integer size,
			String sortBy, String direction) {

		logger.info("Fetching doctors | Page : {} | Size : {} | Sort By : {} | Direction : {}", page, size, sortBy,
				direction);

		// ---------- QUERY BY EXAMPLE ----------

		DoctorEntity doctorEntity = new DoctorEntity();

		Example<DoctorEntity> example = Example.of(doctorEntity);

		// ---------- SORT ----------

		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

		// ---------- PAGINATION ----------

		Pageable pageable = PageRequest.of(page, size, sort);

		// ---------- FETCH DOCTORS ----------

		Page<DoctorEntity> doctorPage = doctorDao.fetchAllDoctors(example, pageable);

		// ---------- ENTITY TO RESPONSE ----------

		Page<DoctorResponse> doctorResponsePage = doctorPage.map(BeanCopyUtils::entityToResponse);

		logger.info("Doctors fetched successfully. Total Records In Current Page : {}",
				doctorResponsePage.getNumberOfElements());

		// ---------- RESPONSE ----------

		ResponseStructure<Page<DoctorResponse>> response = new ResponseStructure<>();

		response.setStatus(HttpStatus.OK.value());
		response.setMessage("Doctors fetched successfully.");
		response.setData(doctorResponsePage);

		return ResponseEntity.ok(response);
	}

	// ---------------- FETCH DOCTOR BY ID ----------------

	@Override
	public ResponseEntity<ResponseStructure<DoctorResponse>> fetchDoctorById(Integer id) {

		logger.info("Fetching doctor with ID : {}", id);

		// ---------- FETCH DOCTOR ----------

		DoctorEntity doctorEntity = doctorDao.fetchDoctorById(id).orElseThrow(() -> {

			logger.warn("Doctor not found with ID : {}", id);

			return new ResourceNotFoundException("Doctor not found with ID : " + id);
		});

		logger.info("Doctor fetched successfully. ID : {}", id);

		// ---------- ENTITY TO RESPONSE ----------

		DoctorResponse doctorResponse = BeanCopyUtils.entityToResponse(doctorEntity);

		// ---------- RESPONSE ----------

		ResponseStructure<DoctorResponse> response = new ResponseStructure<>();

		response.setStatus(HttpStatus.OK.value());
		response.setMessage("Doctor fetched successfully.");
		response.setData(doctorResponse);

		return ResponseEntity.ok(response);
	}
	// ---------------- UPDATE DOCTOR ----------------

	@Override
	public ResponseEntity<ResponseStructure<DoctorResponse>> updateDoctor(Integer id,
			DoctorUpdateRequest doctorUpdateRequest) {

		logger.info("Updating doctor with ID : {}", id);

		// ---------- FETCH EXISTING DOCTOR ----------

		DoctorEntity existingDoctor = doctorDao.fetchDoctorById(id).orElseThrow(() -> {

			logger.warn("Doctor not found with ID : {}", id);

			return new ResourceNotFoundException("Doctor not found with ID : " + id);
		});

		// ---------- CHECK DUPLICATE EMAIL ----------

		if (!existingDoctor.getEmail().equals(doctorUpdateRequest.getEmail())) {

			DoctorEntity emailDoctor = new DoctorEntity();
			emailDoctor.setEmail(doctorUpdateRequest.getEmail());

			if (doctorDao.fetchDoctor(Example.of(emailDoctor)).isPresent()) {

				logger.warn("Duplicate doctor email : {}", doctorUpdateRequest.getEmail());

				throw new DuplicateResourceException("Doctor email already exists.");
			}
		}

		// ---------- CHECK DUPLICATE PHONE ----------

		if (!existingDoctor.getPhone().equals(doctorUpdateRequest.getPhone())) {

			DoctorEntity phoneDoctor = new DoctorEntity();
			phoneDoctor.setPhone(doctorUpdateRequest.getPhone());

			if (doctorDao.fetchDoctor(Example.of(phoneDoctor)).isPresent()) {

				logger.warn("Duplicate doctor phone : {}", doctorUpdateRequest.getPhone());

				throw new DuplicateResourceException("Doctor phone number already exists.");
			}
		}

		// ---------- REQUEST TO ENTITY ----------

		DoctorEntity updatedDoctor = BeanCopyUtils.updateRequestToEntity(doctorUpdateRequest);

		// ---------- PRESERVE EXISTING DATA ----------

		updatedDoctor.setId(existingDoctor.getId());

		updatedDoctor.setPassword(existingDoctor.getPassword());

//		updatedDoctor.setStatus(existingDoctor.getStatus());

		updatedDoctor.setCreatedAt(existingDoctor.getCreatedAt());

		updatedDoctor.setUpdatedAt(existingDoctor.getUpdatedAt());

		// ---------- SAVE ----------

		updatedDoctor = doctorDao.saveDoctor(updatedDoctor);

		logger.info("Doctor updated successfully. ID : {}", id);

		// ---------- ENTITY TO RESPONSE ----------

		DoctorResponse doctorResponse = BeanCopyUtils.entityToResponse(updatedDoctor);

		// ---------- RESPONSE ----------

		ResponseStructure<DoctorResponse> response = new ResponseStructure<>();

		response.setStatus(HttpStatus.OK.value());
		response.setMessage("Doctor updated successfully.");
		response.setData(doctorResponse);

		return ResponseEntity.ok(response);
	}

	// ---------------- RESET DOCTOR PASSWORD ----------------

	@Override
	public ResponseEntity<ResponseStructure<String>> resetDoctorPassword(Integer id,
			DoctorPasswordRequest doctorPasswordRequest) {

		logger.info("Resetting password for doctor ID : {}", id);

		// ---------- FETCH DOCTOR ----------

		DoctorEntity doctorEntity = doctorDao.fetchDoctorById(id).orElseThrow(() -> {

			logger.warn("Doctor not found with ID : {}", id);

			return new ResourceNotFoundException("Doctor not found with ID : " + id);
		});

		// ---------- ENCRYPT PASSWORD ----------

		doctorEntity.setPassword(passwordEncoder.encode(doctorPasswordRequest.getPassword()));

		// ---------- SAVE ----------

		doctorDao.saveDoctor(doctorEntity);

		logger.info("Password reset successfully for doctor ID : {}", id);

		// ---------- RESPONSE ----------

		ResponseStructure<String> response = new ResponseStructure<>();

		response.setStatus(HttpStatus.OK.value());
		response.setMessage("Password updated successfully.");
		response.setData("Doctor password has been reset successfully.");

		return ResponseEntity.ok(response);
	}

	// ---------------- DELETE DOCTOR ----------------

	@Override
	public ResponseEntity<ResponseStructure<String>> deleteDoctor(Integer id) {

		logger.info("Deleting doctor with ID : {}", id);

		// ---------- FETCH DOCTOR ----------

		DoctorEntity doctorEntity = doctorDao.fetchDoctorById(id).orElseThrow(() -> {

			logger.warn("Doctor not found with ID : {}", id);

			return new ResourceNotFoundException("Doctor not found with ID : " + id);
		});

		// ---------- DELETE ----------

		doctorDao.deleteDoctor(doctorEntity);

		logger.info("Doctor deleted successfully. ID : {}", id);

		// ---------- RESPONSE ----------

		ResponseStructure<String> response = new ResponseStructure<>();

		response.setStatus(HttpStatus.OK.value());
		response.setMessage("Doctor deleted successfully.");
		response.setData("Doctor record removed successfully.");

		return ResponseEntity.ok(response);
	}

	// ---------------- SEARCH DOCTORS ----------------

	@Override
	public ResponseEntity<ResponseStructure<Page<DoctorResponse>>> searchDoctors(

			String name,

			String email,

			String phone,

			String department,

			String qualification,

			Integer page,

			Integer size,

			String sortBy,

			String direction) {

		logger.info("Searching doctors...");

		// ---------- CREATE SEARCH OBJECT ----------

		DoctorEntity doctorEntity = new DoctorEntity();

		doctorEntity.setName(name);
		doctorEntity.setEmail(email);
		doctorEntity.setPhone(phone);
		doctorEntity.setDepartment(department);
		doctorEntity.setQualification(qualification);

		// ---------- ONLY ACTIVE DOCTORS ----------

		doctorEntity.setStatus(DoctorStatus.ACTIVE);

		// ---------- EXAMPLE MATCHER ----------

		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreNullValues().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

		Example<DoctorEntity> example = Example.of(doctorEntity, matcher);

		// ---------- SORT ----------

		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

		// ---------- PAGINATION ----------

		Pageable pageable = PageRequest.of(page, size, sort);

		// ---------- FETCH ----------

		Page<DoctorEntity> doctorPage = doctorDao.fetchAllDoctors(example, pageable);

		// ---------- ENTITY TO RESPONSE ----------

		Page<DoctorResponse> doctorResponsePage = doctorPage.map(BeanCopyUtils::entityToResponse);

		// ---------- RESPONSE ----------

		ResponseStructure<Page<DoctorResponse>> response = new ResponseStructure<>();

		response.setStatus(HttpStatus.OK.value());
		response.setMessage("Doctors fetched successfully.");
		response.setData(doctorResponsePage);

		logger.info("Search completed. {} record(s) found.", doctorResponsePage.getNumberOfElements());

		return ResponseEntity.ok(response);
	}

}