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
				return new ResponseEntity<>(err,HttpStatus.OK);
			}
			return new ResponseEntity<>(lista, HttpStatus.OK);
		}catch(Exception e){
			ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.INTERNAL_SERVER_ERROR.toString(),"INTERNAL SERVER ERROR");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
