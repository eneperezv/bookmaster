package com.eenp.bookmaster.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eenp.bookmaster.api.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher,Long> {
	
	List<Publisher> findAll();

}
