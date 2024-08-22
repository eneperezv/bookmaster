package com.eenp.bookmaster.client.controller;

/*
 * @(#)AuthorController.java 1.0 20/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Clase controller para Autores
 *
 * @author eliezer.navarro
 * @version 1.0 | 20/08/2024
 * @since 1.0
 */

import java.io.IOException;

import java.net.URISyntaxException;

import org.apache.http.ParseException;

import com.eenp.bookmaster.client.entity.ApiResponse;
import com.eenp.bookmaster.client.service.AuthorService;

public class AuthorController {
	
	private final AuthorService authorService;
	
	public AuthorController() {
		this.authorService = new AuthorService();
	}

	public ApiResponse<?> getAutores() throws URISyntaxException, ParseException, IOException {
		return authorService.getAutores();
	}

}
