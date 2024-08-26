package com.eenp.bookmaster.client.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.ParseException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.eenp.bookmaster.client.entity.ApiResponse;
import com.eenp.bookmaster.client.entity.Book;
import com.eenp.bookmaster.client.service.BookService;

public class BookController {
	
	private final BookService bookService;
	
	public BookController() {
		this.bookService = new BookService();
	}
	
	public ApiResponse<?> getLibros() throws ParseException, URISyntaxException, IOException{
		return bookService.getLibros();
	}

	public ApiResponse<?> setLibroNuevo(Book book) throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
		return bookService.setLibroNuevo(book);
	}

}
