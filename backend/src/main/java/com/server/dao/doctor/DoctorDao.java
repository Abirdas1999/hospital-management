package com.server.dao.doctor;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.server.entity.DoctorEntity;

public interface DoctorDao {

	// ---------------- SAVE DOCTOR ----------------

	DoctorEntity saveDoctor(DoctorEntity doctorEntity);

	// ---------------- FETCH ALL DOCTORS (QBE + PAGINATION) ----------------

	Page<DoctorEntity> fetchAllDoctors(
			Example<DoctorEntity> example,
			Pageable pageable);

	// ---------------- FETCH DOCTOR BY ID ----------------

	Optional<DoctorEntity> fetchDoctorById(Integer id);

	// ---------------- FETCH DOCTOR (QBE) ----------------

	Optional<DoctorEntity> fetchDoctor(
			Example<DoctorEntity> example);

	// ---------------- FETCH DOCTOR BY EMAIL ----------------

	Optional<DoctorEntity> fetchByEmail(String email);

	// ---------------- DELETE DOCTOR ----------------

	void deleteDoctor(DoctorEntity doctorEntity);

}