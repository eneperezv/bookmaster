package com.eenp.bookmaster.client.entity;

/*
 * @(#)Author.java 1.0 12/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Entidad para controlar los datos del Autores
 *
 * @author eliezer.navarro
 * @version 1.0 | 12/08/2024
 * @since 1.0
 */

public class Author {
	
	private Integer id;
	private String nombre;
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
	@Override
	public String toString() {
		return "Author [id=" + id + ", nombre=" + nombre + "]";
	}

}
