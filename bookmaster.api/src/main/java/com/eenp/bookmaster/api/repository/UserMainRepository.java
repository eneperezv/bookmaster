package com.eenp.bookmaster.api.repository;

/*
 * @(#)UserMainRepository.java 1.0 07/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Interfaz Repositorio para persistencia de Usuarios
 *
 * @author eliezer.navarro
 * @version 1.0 | 07/08/2024
 * @since 1.0
 */

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eenp.bookmaster.api.entity.UserMain;

public interface UserMainRepository extends JpaRepository<UserMain,Long> {
	
	Optional<UserMain> findByUsername(String username);
	
	List<UserMain> findAll();
	
	@Query(value = "SELECT * FROM Users u WHERE u.username = :usuario", nativeQuery = true)
	UserMain findByUsuario(String usuario);

}
