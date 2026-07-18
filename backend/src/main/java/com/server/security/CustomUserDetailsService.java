package com.server.security;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.server.dao.admin.AdminDao;
import com.server.dao.doctor.DoctorDao;
import com.server.entity.AdminEntity;
import com.server.entity.DoctorEntity;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CustomUserDetailsService implements UserDetailsService {

	// ---------------- ADMIN DAO ----------------

	private final AdminDao adminDao;

	// ---------------- DOCTOR DAO ----------------

	private final DoctorDao doctorDao;

	// ---------------- LOAD USER BY EMAIL ----------------

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		// ---------------- CHECK ADMIN ----------------

		AdminEntity adminExample = new AdminEntity();
		adminExample.setEmail(email);

		Optional<AdminEntity> optionalAdmin = adminDao.fetchAdmin(Example.of(adminExample));

		if (optionalAdmin.isPresent()) {

			AdminEntity admin = optionalAdmin.get();

			return new CustomUserDetails(admin.getId(), admin.getEmail(), admin.getPassword(), "ADMIN");
		}

		// ---------------- CHECK DOCTOR ----------------

		Optional<DoctorEntity> optionalDoctor = doctorDao.fetchByEmail(email);

		if (optionalDoctor.isPresent()) {

			DoctorEntity doctor = optionalDoctor.get();

			return new CustomUserDetails(doctor.getId(), doctor.getEmail(), doctor.getPassword(), "DOCTOR");
		}

		// ---------------- USER NOT FOUND ----------------

		throw new UsernameNotFoundException("User not found with email : " + email);
	}
}