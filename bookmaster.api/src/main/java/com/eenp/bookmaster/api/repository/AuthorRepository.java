package com.eenp.bookmaster.api.repository;

/*
 * @(#)AuthorRepository.java 1.0 07/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Interfaz Repositorio para persistencia de Autores
 *
 * @author eliezer.navarro
 * @version 1.0 | 07/08/2024
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eenp.bookmaster.api.entity.Author;

public interface AuthorRepository extends JpaRepository<Author,Long> {
	
	List<Author> findAll();

}
