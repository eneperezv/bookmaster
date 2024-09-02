package com.eenp.bookmaster.client.service;

/*
 * @(#)LoanService.java 1.0 29/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Clase service para Prestamos
 *
 * @author eliezer.navarro
 * @version 1.0 | 29/08/2024
 * @since 1.0
 */

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.ParseException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.eenp.bookmaster.client.data.ApiService;
import com.eenp.bookmaster.client.entity.ApiResponse;
import com.eenp.bookmaster.client.entity.Loan;

public class LoanService {
	
	private final ApiService apiService;
	
	public LoanService() {
		this.apiService = new ApiService();
	}
	
	public ApiResponse<?> getPrestamos() throws ParseException, URISyntaxException, IOException{
		return apiService.getPrestamos();
	}
	
	public ApiResponse<?> setPrestamoNuevo(Loan loan) throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException{
		return apiService.setPrestamoNuevo(loan);
	}

	public ApiResponse<?> getPrestamosByCliente(String nombre) throws ParseException, URISyntaxException, IOException {
		return apiService.getPrestamosByCliente(nombre);
	}

	public ApiResponse<?> setPrestamoUpdate(Loan loan) throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
		return apiService.setPrestamoUpdate(loan);
	}

}
