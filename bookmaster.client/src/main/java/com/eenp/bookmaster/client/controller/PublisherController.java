package com.eenp.bookmaster.client.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.ParseException;

import com.eenp.bookmaster.client.entity.ApiResponse;
import com.eenp.bookmaster.client.service.PublisherService;

public class PublisherController {
	
	private final PublisherService publisherService;
	
	public PublisherController() {
		this.publisherService = new PublisherService();
	}

	public ApiResponse<?> getEditoriales() throws URISyntaxException, ParseException, IOException {
		return publisherService.getEditoriales();
	}

}
