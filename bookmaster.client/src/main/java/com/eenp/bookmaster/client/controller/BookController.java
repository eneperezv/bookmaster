package com.eenp.bookmaster.client.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.ParseException;

import com.eenp.bookmaster.client.entity.ApiResponse;
import com.eenp.bookmaster.client.service.BookService;

public class BookController {
	
	private final BookService bookService;
	
	public BookController() {
		this.bookService = new BookService();
	}
	
	public ApiResponse<?> getLibros() throws ParseException, URISyntaxException, IOException{
		return bookService.getLibros();
	}

}
