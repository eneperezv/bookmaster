package com.eenp.bookmaster.client.service;

import java.net.URISyntaxException;

import com.eenp.bookmaster.client.data.ApiService;
import com.eenp.bookmaster.client.entity.User;

public class UserService {
	
	private final ApiService apiService;
	
	public UserService() {
        this.apiService = new ApiService();
    }
	
	public User getDatosUsuario(String usuario,String clave) throws URISyntaxException {
		return apiService.getDatosUsuario(usuario,clave);
	}

}
