package com.eenp.bookmaster.client.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.ParseException;

import com.eenp.bookmaster.client.data.ApiService;
import com.eenp.bookmaster.client.entity.ApiResponse;

public class PublisherService {
	
	private final ApiService apiService;
	
	public PublisherService() {
		this.apiService = new ApiService();
	}

	public ApiResponse<?> getEditoriales() throws URISyntaxException, ParseException, IOException {
		return apiService.getEditoriales();
	}

}
