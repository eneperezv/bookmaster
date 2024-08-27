package com.eenp.bookmaster.client.entity;

public class Book {
	
	private Integer id;
	private String titulo;
	private Integer idautor;
	private Author author;
	private Integer ideditorial;
	private Publisher publisher;
	private Integer aniopublicacion;
	private Integer disponible;
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
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Integer getIdeditorial() {
		return ideditorial;
	}
	public void setIdeditorial(Integer ideditorial) {
		this.ideditorial = ideditorial;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	public Integer getAniopublicacion() {
		return aniopublicacion;
	}
	public void setAniopublicacion(Integer aniopublicacion) {
		this.aniopublicacion = aniopublicacion;
	}
	public Integer getDisponible() {
		return disponible;
	}
	public void setDisponible(Integer disponible) {
		this.disponible = disponible;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", titulo=" + titulo + ", idautor=" + idautor + ", author=" + author
				+ ", ideditorial=" + ideditorial + ", publisher=" + publisher + ", aniopublicacion=" + aniopublicacion
				+ ", disponible=" + disponible + "]";
	}

}
