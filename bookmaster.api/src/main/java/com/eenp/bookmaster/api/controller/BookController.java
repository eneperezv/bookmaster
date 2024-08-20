package com.eenp.bookmaster.api.controller;

/*
 * @(#)BookController.java 1.0 08/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Clase Controller para la gestion de Libros
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eenp.bookmaster.api.entity.Book;
import com.eenp.bookmaster.api.entity.ErrorDetails;
import com.eenp.bookmaster.api.repository.BookRepository;

@RestController
@RequestMapping("/api/bookmaster")
public class BookController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	BookRepository bookRepository;
	
	@GetMapping("/book/todos")
	public ResponseEntity<?> getLibros(){
		List<Book> lista = new ArrayList<Book>();
		try{
			bookRepository.findAll().forEach(lista::add);
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

}
