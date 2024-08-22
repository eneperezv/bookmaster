package com.eenp.bookmaster.client.entity;

/*
 * @(#)Token.java 1.0 12/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Entidad para controlar los datos del Token
 *
 * @author eliezer.navarro
 * @version 1.0 | 12/08/2024
 * @since 1.0
 */

public class Token {
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "Token [token=" + token + "]";
	}

}
