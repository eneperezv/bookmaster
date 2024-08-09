package com.eenp.bookmaster.api.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="IdLibro", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="titulo")
	private String titulo;
	
	@Column(name="idautor")
	private Integer idautor;
	
	@Column(name="ideditorial")
	private Integer ideditorial;
	
	@Column(name="aniopublicacion")
	private Integer aniopublicacion;
	
	public Book() {
		
	}

	public Book(String titulo, Integer idautor, Integer ideditorial, Integer aniopublicacion) {
		super();
		this.titulo = titulo;
		this.idautor = idautor;
		this.ideditorial = ideditorial;
		this.aniopublicacion = aniopublicacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getIdautor() {
		return idautor;
	}

	public void setIdautor(Integer idautor) {
		this.idautor = idautor;
	}

	public Integer getIdeditorial() {
		return ideditorial;
	}

	public void setIdeditorial(Integer ideditorial) {
		this.ideditorial = ideditorial;
	}

	public Integer getAniopublicacion() {
		return aniopublicacion;
	}

	public void setAniopublicacion(Integer aniopublicacion) {
		this.aniopublicacion = aniopublicacion;
	}

	@Override
	public String toString() {
		return "Books [id=" + id + ", titulo=" + titulo + ", idautor=" + idautor + ", ideditorial=" + ideditorial
				+ ", aniopublicacion=" + aniopublicacion + "]";
	}

}
