package com.eenp.bookmaster.api.controller;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eenp.bookmaster.api.entity.ErrorDetails;
import com.eenp.bookmaster.api.entity.UserMain;
import com.eenp.bookmaster.api.repository.UserMainRepository;

@RestController
@RequestMapping("/api/bookmaster")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserMainRepository userMainRepository;
	
	@GetMapping("/user/todos")
	public ResponseEntity<?> getUsuarios(){
		List<UserMain> lista = new ArrayList<UserMain>();
		try{
			userMainRepository.findAll().forEach(lista::add);
			if(lista.isEmpty()) {
				ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.NO_CONTENT.toString(),"NO CONTENT");
				return new ResponseEntity<>(err,HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(lista, HttpStatus.OK);
		}catch(Exception e){
			ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.NO_CONTENT.toString(),"INTERNAL SERVER ERROR");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/user/{usuario}")
	public ResponseEntity<?> getUsuarioByName(@PathVariable("usuario") String usuario){
		UserMain result;
		try{
			result = userMainRepository.findByUsuario(usuario);
			if(result == null) {
				ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.NOT_FOUND.toString(),"Usuario <"+usuario+"> no existe");
				logger.error(err.toString());
				return new ResponseEntity<ErrorDetails>(err,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<UserMain>(result, HttpStatus.OK);
		}catch(Exception e){
			ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.NO_CONTENT.toString(),"INTERNAL SERVER ERROR");
			return new ResponseEntity<ErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/user/create")
	public ResponseEntity<?> createUsuario(@RequestBody UserMain user){
		UserMain savedUser;
		try{
			savedUser = userMainRepository.save(user);
			if(savedUser == null) {
				ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.NOT_FOUND.toString(),"Usuario <"+user+"> no existe");
				logger.error(err.toString());
				return new ResponseEntity<ErrorDetails>(err,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<UserMain>(savedUser, HttpStatus.CREATED);
		}catch(Exception e){
			ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.INTERNAL_SERVER_ERROR.toString(),"INTERNAL SERVER ERROR");
			return new ResponseEntity<ErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/user/update")
	public ResponseEntity<?> updateUsuario(@RequestBody UserMain user) {
	    try {
	    	UserMain result = userMainRepository.findByUsuario(user.getUsername());
	        if (result == null) {
	            ErrorDetails err = new ErrorDetails(new Date(), HttpStatus.NOT_FOUND.toString(), "Usuario <" + user.getUsername() + "> no existe");
	            logger.error(err.toString());
	            return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	        }
	        result.setPassword(user.getPassword());

	        UserMain savedUser = userMainRepository.save(user);
	        if(savedUser == null) {
				ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.NOT_FOUND.toString(),"Usuario <"+user+"> no existe");
				logger.error(err.toString());
				return new ResponseEntity<ErrorDetails>(err,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<UserMain>(savedUser, HttpStatus.OK);
	    } catch (Exception e) {
	        ErrorDetails err = new ErrorDetails(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), "INTERNAL SERVER ERROR");
	        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
}
