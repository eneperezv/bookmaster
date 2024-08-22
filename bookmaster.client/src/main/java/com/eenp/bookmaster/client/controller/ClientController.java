package com.eenp.bookmaster.client.controller;

/*
 * @(#)ClientController.java 1.0 13/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Clase controller para Clientes
 *
 * @author eliezer.navarro
 * @version 1.0 | 13/08/2024
 * @since 1.0
 */

import java.io.IOException;

import java.net.URISyntaxException;

import org.apache.http.ParseException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.eenp.bookmaster.client.entity.ApiResponse;
import com.eenp.bookmaster.client.entity.Client;
import com.eenp.bookmaster.client.service.ClientService;

public class ClientController {
	
	private final ClientService clientService;

    public ClientController() {
        this.clientService = new ClientService();
    }
	
	public ApiResponse<?> getClientes() throws URISyntaxException, ParseException, IOException {
    	return clientService.getClientes();
    }
	
	public ApiResponse<?> setClienteNuevo(Client cliente) throws URISyntaxException, JsonGenerationException, JsonMappingException, IOException{
		return clientService.setClienteNuevo(cliente);
	}

}
