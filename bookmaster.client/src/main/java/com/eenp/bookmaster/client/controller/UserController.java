package com.eenp.bookmaster.client.controller;

/*
 * @(#)UserController.java 1.0 07/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Clase controller para Usuarios
 *
 * @author eliezer.navarro
 * @version 1.0 | 07/08/2024
 * @since 1.0
 */

import java.io.IOException;

import java.net.URISyntaxException;

import org.apache.http.ParseException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.eenp.bookmaster.client.entity.ApiResponse;
import com.eenp.bookmaster.client.entity.User;
import com.eenp.bookmaster.client.service.UserService;

public class UserController {
	
	private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }
    
    public ApiResponse<?> getToken(User user) throws URISyntaxException{
    	return userService.getToken(user);
    }
    
    public ApiResponse<?> getDatosUsuario(User req) throws URISyntaxException, ParseException, IOException {
    	return userService.getDatosUsuario(req);
    }
    
    public ApiResponse<?> getUsuarios() throws URISyntaxException, ParseException, IOException {
		return userService.getUsuarios();
	}
    
    public ApiResponse<?> setUsuarioNuevo(User user) throws URISyntaxException, JsonGenerationException, JsonMappingException, IOException {
		return userService.setUsuarioNuevo(user);
	}
	
	public ApiResponse<?> setUsuarioUpdate(User user) throws URISyntaxException, JsonGenerationException, JsonMappingException, IOException {
		return userService.setUsuarioUpdate(user);
	}

}
