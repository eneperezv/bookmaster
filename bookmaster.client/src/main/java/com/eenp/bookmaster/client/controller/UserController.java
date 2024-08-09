package com.eenp.bookmaster.client.controller;

import com.eenp.bookmaster.client.data.ApiService;
import com.eenp.bookmaster.client.entity.User;
import com.eenp.bookmaster.client.service.UserService;

public class UserController {
	
	private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }
    
    public User obtenerDatosUsuario(String nombreUsuario) {
    	System.out.println("ENTRO A UserController");
    	return userService.getDatosUsuario(nombreUsuario);
        // Procesa los datos y actualiza la interfaz gráfica
        // (por ejemplo, muestra los datos en una ventana)
    }

}
