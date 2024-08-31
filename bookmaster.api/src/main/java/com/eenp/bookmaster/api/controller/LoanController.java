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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eenp.bookmaster.api.entity.Book;
import com.eenp.bookmaster.api.entity.ErrorDetails;
import com.eenp.bookmaster.api.entity.Loan;
import com.eenp.bookmaster.api.repository.LoanRepository;

@RestController
@RequestMapping("/api/bookmaster")
public class LoanController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoanController.class);
	
	@Autowired
	LoanRepository loanRepository;
	
	@GetMapping("/loan/todos")
	public ResponseEntity<?> getPrestamos(){
		List<Loan> lista = new ArrayList<Loan>();
		try{
			loanRepository.findAll().forEach(lista::add);
			if(lista.isEmpty()) {
				ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.OK.toString(),"NO CONTENT");
				System.out.println("err-1->"+err.toString());
				return new ResponseEntity<ErrorDetails>(err,HttpStatus.OK);
			}
			return new ResponseEntity<List<Loan>>(lista, HttpStatus.OK);
		}catch(Exception e){
			ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.INTERNAL_SERVER_ERROR.toString(),"INTERNAL SERVER ERROR-->"+e.getMessage()+"<--");
			System.out.println("err-2->"+err.toString());
			return new ResponseEntity<ErrorDetails>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/loan/create")
	public ResponseEntity<?> setPrestamo(@RequestBody Loan loan){
		Loan savedLoan;
		try{
			savedLoan = loanRepository.save(loan);
			if(savedLoan == null) {
				ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.NOT_FOUND.toString(),"Prestamo <"+loan+"> no registrado");
				return new ResponseEntity<ErrorDetails>(err,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Loan>(savedLoan, HttpStatus.CREATED);
		}catch(Exception e){
			ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.INTERNAL_SERVER_ERROR.toString(),"INTERNAL SERVER ERROR");
			return new ResponseEntity<ErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
