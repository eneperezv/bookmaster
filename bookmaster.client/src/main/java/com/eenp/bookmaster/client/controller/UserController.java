package com.eenp.bookmaster.client.controller;

import java.net.URISyntaxException;

import com.eenp.bookmaster.client.entity.User;
import com.eenp.bookmaster.client.service.UserService;

public class UserController {
	
	private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }
    
    public User obtenerDatosUsuario(String nombreUsuario) throws URISyntaxException {
    	return userService.getDatosUsuario(nombreUsuario);
    }

}
