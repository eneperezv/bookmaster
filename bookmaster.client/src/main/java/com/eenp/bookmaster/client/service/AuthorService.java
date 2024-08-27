package com.eenp.bookmaster.client.service;

/*
 * @(#)AuthorService.java 1.0 20/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/** Capa de Servicio para el control de Autores
 *
 * @author eliezer.navarro
 * @version 1.0 | 20/08/2024
 * @since 1.0
 */

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.ParseException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.eenp.bookmaster.client.data.ApiService;
import com.eenp.bookmaster.client.entity.ApiResponse;
import com.eenp.bookmaster.client.entity.Author;

public class AuthorService {
	
	private final ApiService apiService;
	
	public AuthorService() {
		this.apiService = new ApiService();
	}

	public ApiResponse<?> getAutores() throws URISyntaxException, ParseException, IOException {
		return apiService.getAutores();
	}

	public ApiResponse<?> setAutorNuevo(Author author) throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
		return apiService.setAutorNuevo(author);
	}

}
