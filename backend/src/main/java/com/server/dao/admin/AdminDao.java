package com.server.dao.admin;

import java.util.Optional;

import org.springframework.data.domain.Example;

import com.server.entity.AdminEntity;

public interface AdminDao {

	// ---------------- SAVE ADMIN ----------------

	AdminEntity saveAdmin(AdminEntity adminEntity);

	// ---------------- FETCH BY ID ----------------

	Optional<AdminEntity> fetchById(Integer id);


	// ---------------- FETCH BY EMAIL ----------------

	Optional<AdminEntity> fetchAdmin(Example<AdminEntity> example);

}