package com.eenp.bookmaster.api.repository;

/*
 * @(#)ClientRepository.java 1.0 07/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Interfaz Repositorio para persistencia de Clientes
 *
 * @author eliezer.navarro
 * @version 1.0 | 07/08/2024
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eenp.bookmaster.api.entity.Client;

public interface ClientRepository extends JpaRepository<Client,Long> {
	
	List<Client> findAll();

}
