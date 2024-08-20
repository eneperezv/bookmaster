package com.eenp.bookmaster.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eenp.bookmaster.api.entity.UserMain;

public interface UserMainRepository extends JpaRepository<UserMain,Long> {
	
	Optional<UserMain> findByUsername(String username);
	
	List<UserMain> findAll();
	
	@Query(value = "SELECT * FROM Users u WHERE u.usuario = :usuario", nativeQuery = true)
	UserMain findByUsuario(String usuario);

}
