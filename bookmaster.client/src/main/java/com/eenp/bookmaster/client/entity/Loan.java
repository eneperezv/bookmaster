package com.eenp.bookmaster.client.entity;

public class Loan {
	
	private Integer id;
	private Book id_libro;
	private Client id_cliente;
	private String fechaPrestamo;
	private String fechaDevolucion;
	private String estado;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Book getId_libro() {
		return id_libro;
	}
	public void setId_libro(Book id_libro) {
		this.id_libro = id_libro;
	}
	public Client getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Client id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getFechaPrestamo() {
		return fechaPrestamo;
	}
	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
	public String getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Loan [id=" + id + ", id_libro=" + id_libro + ", id_cliente=" + id_cliente + ", fechaPrestamo="
				+ fechaPrestamo + ", fechaDevolucion=" + fechaDevolucion + ", estado=" + estado + "]";
	}
	
	

}
