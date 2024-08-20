package com.eenp.bookmaster.api.entity;

/*
 * @(#)Publisher.java 1.0 07/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Entidad para control de Editoriaales
 *
 * @author eliezer.navarro
 * @version 1.0 | 07/08/2024
 * @since 1.0
 */

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Publishers")
public class Publisher implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="IdPublisher", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	public Publisher() {
		
	}

	public Publisher(String nombre) {
		super();
		this.nombre = nombre;
	}

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
		return "Publisher [id=" + id + ", nombre=" + nombre + "]";
	}

}
