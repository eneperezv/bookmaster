package com.eenp.bookmaster.client.service;

/*
 * @(#)PublisherService.java 1.0 13/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/** Capa de Servicio para el control de Editoriales
 *
 * @author eliezer.navarro
 * @version 1.0 | 13/08/2024
 * @since 1.0
 */

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.ParseException;

import com.eenp.bookmaster.client.data.ApiService;
import com.eenp.bookmaster.client.entity.ApiResponse;

public class PublisherService {
	
	private final ApiService apiService;
	
	public PublisherService() {
		this.apiService = new ApiService();
	}

	public ApiResponse<?> getEditoriales() throws URISyntaxException, ParseException, IOException {
		return apiService.getEditoriales();
	}

}
