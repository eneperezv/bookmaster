package com.eenp.bookmaster.client.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.ParseException;

import com.eenp.bookmaster.client.data.ApiService;
import com.eenp.bookmaster.client.entity.ApiResponse;

public class BookService {
	
	private final ApiService apiService;
	
	public BookService() {
		this.apiService = new ApiService();
	}

	public ApiResponse<?> getLibros() throws ParseException, URISyntaxException, IOException {
		return apiService.getLibros();
	}

}
