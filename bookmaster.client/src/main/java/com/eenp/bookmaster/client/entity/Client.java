package com.eenp.bookmaster.client.entity;

/*
 * @(#)Client.java 1.0 12/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Entidad para controlar los datos del cliente
 *
 * @author eliezer.navarro
 * @version 1.0 | 12/08/2024
 * @since 1.0
 */

public class Client {
	
	private Integer id;
	private String nombre;
	private String correoelectronico;
	private String direccion;
	private String telefono;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreoelectronico() {
		return correoelectronico;
	}
	public void setCorreoelectronico(String correoelectronico) {
		this.correoelectronico = correoelectronico;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", nombre=" + nombre + ", correoelectronico=" + correoelectronico + ", direccion="
				+ direccion + ", telefono=" + telefono + "]";
	}

}
