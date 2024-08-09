package com.eenp.bookmaster.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eenp.bookmaster.api.entity.Author;

public interface AuthorRepository extends JpaRepository<Author,Long> {
	
	List<Author> findAll();

}
