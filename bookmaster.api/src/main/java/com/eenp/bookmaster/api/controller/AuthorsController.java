package com.eenp.bookmaster.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eenp.bookmaster.api.entity.Author;

@RestController
@RequestMapping("/api/bookmaster")
public class AuthorsController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorsController.class);
	
	@GetMapping("/author/todos")
	public List<Author> getAutores(){ //public ResponseEntity<?>
		List<Author> lista = new ArrayList<Author>();
		lista.add(new Author("Anne Rice"));
		lista.add(new Author("J.K.Rowling"));
		lista.add(new Author("John Katzenbach"));
		return lista;
		/*
		try{
			userRepository.findAll().forEach(lista::add);
			if(lista.isEmpty()) {
				ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.NO_CONTENT.toString(),"NO CONTENT");
				return new ResponseEntity<>(err,HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(lista, HttpStatus.OK);
		}catch(Exception e){
			ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.NO_CONTENT.toString(),"INTERNAL SERVER ERROR");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		*/
	}

}
