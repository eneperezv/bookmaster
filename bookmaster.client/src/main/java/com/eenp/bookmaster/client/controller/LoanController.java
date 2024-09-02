package com.eenp.bookmaster.client.controller;

/*
 * @(#)LoanController.java 1.0 29/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Clase controller para Prestamos
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

import com.eenp.bookmaster.client.entity.ApiResponse;
import com.eenp.bookmaster.client.entity.Loan;
import com.eenp.bookmaster.client.service.LoanService;

public class LoanController {
	
	private final LoanService loanService;
	
	public LoanController() {
		this.loanService = new LoanService();
	}
	
	public ApiResponse<?> getPrestamos() throws ParseException, URISyntaxException, IOException{
		return loanService.getPrestamos();
	}
	
	public ApiResponse<?> setPrestamoNuevo(Loan loan) throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException{
		return loanService.setPrestamoNuevo(loan);
	}
	
	public ApiResponse<?> getPrestamosByCliente(String nombre) throws ParseException, URISyntaxException, IOException{
		return loanService.getPrestamosByCliente(nombre);
	}

	public ApiResponse<?> setPrestamoUpdate(Loan loan) throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
		return loanService.setPrestamoUpdate(loan);
	}

}
