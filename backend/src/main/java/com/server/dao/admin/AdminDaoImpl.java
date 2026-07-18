package com.server.dao.admin;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import com.server.entity.AdminEntity;
import com.server.repository.admin.AdminRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AdminDaoImpl implements AdminDao {

	// ---------------- ADMIN REPOSITORY ----------------

	private final AdminRepository adminRepository;

	// ---------------- SAVE ADMIN ----------------

	@Override
	public AdminEntity saveAdmin(AdminEntity adminEntity) {

		return adminRepository.save(adminEntity);

	}

	// ---------------- FETCH BY ID ----------------

	@Override
	public Optional<AdminEntity> fetchById(Integer id) {

		return adminRepository.findById(id);

	}



	// ---------------- FETCH BY EMAIL ----------------

	@Override
	public Optional<AdminEntity> fetchAdmin(Example<AdminEntity> example) {

		return adminRepository.findOne(example);

	}

}