package com.eenp.bookmaster.client.service;

/*
 * @(#)UserService.java 1.0 07/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/** Capa de Servicio para el control de Usuarios
 *
 * @author eliezer.navarro
 * @version 1.0 | 07/08/2024
 * @since 1.0
 */

import java.net.URISyntaxException;

import com.eenp.bookmaster.client.data.ApiService;
import com.eenp.bookmaster.client.entity.ApiResponse;
import com.eenp.bookmaster.client.entity.User;

public class UserService {
	
	private final ApiService apiService;
	
	public UserService() {
        this.apiService = new ApiService();
    }

	public ApiResponse<?> getToken(User user) throws URISyntaxException {
		return apiService.getToken(user);
	}
	
	public ApiResponse<?> getDatosUsuario(User req) throws URISyntaxException {
		return apiService.getDatosUsuario(req);
	}
	
	public ApiResponse<?> getUsuarios() throws URISyntaxException {
		return apiService.getUsuarios();
	}
	
	public ApiResponse<?> setUsuarioNuevo(User user) throws URISyntaxException {
		return apiService.setUsuarioNuevo(user);
	}
	
	public ApiResponse<?> setUsuarioUpdate(User user) throws URISyntaxException {
		return apiService.setUsuarioUpdate(user);
	}

}
