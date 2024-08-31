package com.eenp.bookmaster.client.controller;

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

}
