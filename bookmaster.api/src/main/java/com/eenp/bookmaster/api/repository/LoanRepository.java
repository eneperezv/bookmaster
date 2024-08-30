package com.eenp.bookmaster.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eenp.bookmaster.api.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan,Long> {
	
	List<Loan> findAll();

}
