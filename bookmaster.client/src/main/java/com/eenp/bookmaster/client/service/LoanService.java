package com.eenp.bookmaster.client.service;

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

}
