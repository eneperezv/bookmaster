package com.eenp.bookmaster.client.entity;

import java.io.Serializable;

/*
 * @(#)Loan.java 1.0 30/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Entidad para controlar los Prestamos
 *
 * @author eliezer.navarro
 * @version 1.0 | 30/08/2024
 * @since 1.0
 */

public class Loan implements Serializable {
	
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
