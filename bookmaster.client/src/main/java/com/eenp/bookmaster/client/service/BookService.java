package com.eenp.bookmaster.client.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.ParseException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.eenp.bookmaster.client.data.ApiService;
import com.eenp.bookmaster.client.entity.ApiResponse;
import com.eenp.bookmaster.client.entity.Book;

public class BookService {
	
	private final ApiService apiService;
	
	public BookService() {
		this.apiService = new ApiService();
	}

	public ApiResponse<?> getLibros() throws ParseException, URISyntaxException, IOException {
		return apiService.getLibros();
	}

	public ApiResponse<?> setLibroNuevo(Book book) throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
		return apiService.setLibroNuevo(book);
	}

}
