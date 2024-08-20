package com.eenp.bookmaster.client.entity;

/*
 * @(#)User.java 1.0 07/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Entidad para controlar los datos del usuario
 *
 * @author eliezer.navarro
 * @version 1.0 | 11/08/2024
 * @since 1.0
 */

public class User {
	
	private Integer id;
	private String username;
	private String name;
	private String password;
	private String claveNE;
	private String role;
	private String token;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getClaveNE() {
		return claveNE;
	}
	public void setClaveNE(String claveNE) {
		this.claveNE = claveNE;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", name=" + name + ", password=" + password + ", claveNE="
				+ claveNE + ", role=" + role + ", token=" + token + "]";
	}
	
}
