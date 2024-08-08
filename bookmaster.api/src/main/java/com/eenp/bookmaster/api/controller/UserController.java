package com.eenp.bookmaster.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

/*
 * @(#)UserController.java 1.0 08/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Clase Controller para la gestion de Usuarios
 *
 * @author eliezer.navarro
 * @version 1.0 | 08/08/2024
 * @since 1.0
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eenp.bookmaster.api.entity.User;

@RestController
@RequestMapping("/api/bookmaster")
public class UserController {
	
	@GetMapping("/allusers")
	public List<User> getUsuarios(){
		List<User> lista = new ArrayList<User>();
		lista.add(new User("eenp","Eliezer Navarro","eenp"));
		lista.add(new User("admin","Administrador del Sistema","admin"));
		return lista;
	}

}
