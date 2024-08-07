package com.eenp.bookmaster.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eenp.bookmaster.api.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	List<User> findAll();
	
	@Query(value = "SELECT * FROM Users u WHERE u.usuario = :usuario", nativeQuery = true)
	User findByUsuario(String usuario);

}
