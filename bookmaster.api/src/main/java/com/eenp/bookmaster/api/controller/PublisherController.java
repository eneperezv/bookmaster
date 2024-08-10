package com.eenp.bookmaster.api.controller;

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
import com.eenp.bookmaster.api.entity.Publisher;
import com.eenp.bookmaster.api.repository.BookRepository;
import com.eenp.bookmaster.api.repository.PublisherRepository;

@RestController
@RequestMapping("/api/bookmaster")
public class PublisherController {
	
	private static final Logger logger = LoggerFactory.getLogger(PublisherController.class);
	
	@Autowired
	PublisherRepository publisherRepository;
	
	@GetMapping("/publisher/todos")
	public ResponseEntity<?> getAutores(){
		List<Publisher> lista = new ArrayList<Publisher>();
		try{
			publisherRepository.findAll().forEach(lista::add);
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