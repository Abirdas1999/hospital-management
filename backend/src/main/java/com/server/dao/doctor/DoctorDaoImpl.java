package com.server.dao.doctor;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.server.entity.DoctorEntity;
import com.server.repository.doctor.DoctorRepository;
import com.server.security.CustomUserDetailsService;
import com.server.security.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor

public class DoctorDaoImpl implements DoctorDao {

	// ---------------- DOCTOR REPOSITORY ----------------

	private final DoctorRepository doctorRepository;

	// ---------------- SAVE DOCTOR ----------------

	@Override
	public DoctorEntity saveDoctor(DoctorEntity doctorEntity) {

		return doctorRepository.save(doctorEntity);

	}

	// ---------------- FETCH ALL DOCTORS ----------------

	@Override
	public Page<DoctorEntity> fetchAllDoctors(
			Example<DoctorEntity> example,
			Pageable pageable) {

		return doctorRepository.findAll(example, pageable);

	}

	// ---------------- FETCH DOCTOR BY ID ----------------

	@Override
	public Optional<DoctorEntity> fetchDoctorById(Integer id) {

		return doctorRepository.findById(id);

	}

	// ---------------- FETCH DOCTOR (QBE) ----------------

	@Override
	public Optional<DoctorEntity> fetchDoctor(
			Example<DoctorEntity> example) {

		return doctorRepository.findOne(example);

	}

	// ---------------- FETCH DOCTOR BY EMAIL ----------------

	@Override
	public Optional<DoctorEntity> fetchByEmail(String email) {

		DoctorEntity doctorEntity = new DoctorEntity();

		doctorEntity.setEmail(email);

		Example<DoctorEntity> example = Example.of(doctorEntity);

		return doctorRepository.findOne(example);

	}

	// ---------------- DELETE DOCTOR ----------------

	@Override
	public void deleteDoctor(DoctorEntity doctorEntity) {

		doctorRepository.delete(doctorEntity);

	}

}