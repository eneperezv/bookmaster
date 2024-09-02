package com.eenp.bookmaster.api.repository;

/*
 * @(#)LoanRepository.java 1.0 29/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Entidad para control de Clientes
 *
 * @author eliezer.navarro
 * @version 1.0 | 29/08/2024
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eenp.bookmaster.api.entity.Book;
import com.eenp.bookmaster.api.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan,Long> {
	
	List<Loan> findAll();
	
	@Query(value = "SELECT l.* FROM loans l WHERE l.id_prestamo = :idloan", nativeQuery = true)
	Loan findByIdentificador(Integer idloan);

	@Query(value = "SELECT l.* FROM loans l INNER JOIN clients c ON l.id_cliente = c.id_cliente WHERE c.nombre LIKE %:clientnombre% AND l.estado = 1;", nativeQuery = true)
	List<Loan> findByClientNombre(String clientnombre);

}
