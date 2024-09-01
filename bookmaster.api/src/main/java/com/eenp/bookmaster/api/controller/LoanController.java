package com.eenp.bookmaster.api.controller;

import java.time.LocalDate;

/*
 * @(#)LoanController.java 1.0 13/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Clase controller para Autores
 *
 * @author eliezer.navarro
 * @version 1.0 | 13/08/2024
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eenp.bookmaster.api.entity.Book;
import com.eenp.bookmaster.api.entity.ErrorDetails;
import com.eenp.bookmaster.api.entity.Loan;
import com.eenp.bookmaster.api.repository.BookRepository;
import com.eenp.bookmaster.api.repository.LoanRepository;

@RestController
@RequestMapping("/api/bookmaster")
public class LoanController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoanController.class);
	
	@Autowired
	LoanRepository loanRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@GetMapping("/loan/todos")
	public ResponseEntity<?> getPrestamos(){
		List<Loan> lista = new ArrayList<Loan>();
		try{
			loanRepository.findAll().forEach(lista::add);
			if(lista.isEmpty()) {
				ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.OK.toString(),"NO CONTENT");
				return new ResponseEntity<ErrorDetails>(err,HttpStatus.OK);
			}
			return new ResponseEntity<List<Loan>>(lista, HttpStatus.OK);
		}catch(Exception e){
			ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.INTERNAL_SERVER_ERROR.toString(),"INTERNAL SERVER ERROR-->"+e.getMessage()+"<--");
			return new ResponseEntity<ErrorDetails>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/loan/create")
	public ResponseEntity<?> setPrestamo(@RequestBody Loan loan){
		Loan savedLoan;
		Book savedBook;
		Book finalBook = null;
		try{
			savedLoan = loanRepository.save(loan);
			if(savedLoan == null) {
				ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.NOT_FOUND.toString(),"Prestamo <"+loan+"> no registrado");
				return new ResponseEntity<ErrorDetails>(err,HttpStatus.NOT_FOUND);
			}
			savedBook = bookRepository.findByIdentificador(loan.getId_libro().getId());
			if(savedBook != null) {
				savedBook.setDisponible(2);
				finalBook = bookRepository.save(savedBook);
			}
			savedLoan.setId_libro(finalBook);
			return new ResponseEntity<Loan>(savedLoan, HttpStatus.OK);
		}catch(Exception e){
			ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.INTERNAL_SERVER_ERROR.toString(),"INTERNAL SERVER ERROR");
			return new ResponseEntity<ErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/loan/update")
	public ResponseEntity<?> updatePrestamo(@RequestBody Loan loan){
		try{
			Loan savedLoan = loanRepository.findByIdentificador(loan.getId());
			if(savedLoan == null) {
				ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.NOT_FOUND.toString(),"Prestamo <"+loan+"> no registrado");
				return new ResponseEntity<ErrorDetails>(err,HttpStatus.NOT_FOUND);
			}
			savedLoan.setFechaDevolucion(LocalDate.now().toString());
			savedLoan.setEstado("2");
			
			Loan finalLoan = loanRepository.save(savedLoan);
			if(finalLoan == null) {
				ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.NOT_FOUND.toString(),"Prestamo <"+finalLoan+"> no registrado");
				return new ResponseEntity<ErrorDetails>(err,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Loan>(finalLoan, HttpStatus.OK);
		}catch(Exception e){
			ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.INTERNAL_SERVER_ERROR.toString(),"INTERNAL SERVER ERROR");
			return new ResponseEntity<ErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
