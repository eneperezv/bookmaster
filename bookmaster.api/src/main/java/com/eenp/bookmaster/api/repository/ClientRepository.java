package com.eenp.bookmaster.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eenp.bookmaster.api.entity.Client;

public interface ClientRepository extends JpaRepository<Client,Long> {
	
	List<Client> findAll();

}
