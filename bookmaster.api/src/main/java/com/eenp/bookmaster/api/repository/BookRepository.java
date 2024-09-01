package com.eenp.bookmaster.api.repository;

/*
 * @(#)BookRepository.java 1.0 07/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Interfaz Repositorio para persistencia de Libros
 *
 * @author eliezer.navarro
 * @version 1.0 | 07/08/2024
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eenp.bookmaster.api.entity.Book;
import com.eenp.bookmaster.api.entity.UserMain;

public interface BookRepository extends JpaRepository<Book,Long> {
	
	List<Book> findAll();
	
	@Query(value = "SELECT b.* FROM books b INNER JOIN authors a ON b.idautor = a.id_autor WHERE a.nombre LIKE %:authorName% AND b.disponible in ('1','2')", nativeQuery = true)
	List<Book> findByAuthorNombre(String authorName);
	
	@Query(value = "SELECT b.* FROM books b WHERE b.titulo LIKE %:titulo% AND b.disponible in ('1','2')", nativeQuery = true)
	List<Book> findByTitulo(String titulo);

}
