package com.eenp.bookmaster.client.controller;

import com.eenp.bookmaster.client.service.BookService;

public class BookController {
	
	private final BookService bookService;
	
	public BookController() {
		this.bookService = new BookService();
	}

}
