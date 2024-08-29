package com.eenp.bookmaster.api.entity;

/*
 * @(#)Loan.java 1.0 29/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Entidad para el control de Prestamos
 *
 * @author eliezer.navarro
 * @version 1.0 | 29/08/2024
 * @since 1.0
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Loans")
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="IdPrestamo", unique=true, nullable=false)
	private Integer id;
	
	@JoinColumn(name = "id_libro", nullable = false)
	@ManyToOne(targetEntity=Book.class, fetch=FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Integer id_libro;
	
	@JoinColumn(name = "id_cliente", nullable = false)
	@ManyToOne(targetEntity=Client.class, fetch=FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Integer id_cliente;
	
	@Column(name="fecha_prestamo")
	private String fechaPrestamo;
	
	@Column(name="fecha_devolucion")
	private String fechaDevolucion;
	
	@Column(name="estado")
	private String estado;
	/* 1 -> ACTIVO
	 * 2 -> DEVUELTO */
	
	public Loan() {
		
	}

	public Loan(Integer id_libro, Integer id_cliente, String fechaPrestamo, String fechaDevolucion, String estado) {
		super();
		this.id_libro = id_libro;
		this.id_cliente = id_cliente;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_libro() {
		return id_libro;
	}

	public void setId_libro(Integer id_libro) {
		this.id_libro = id_libro;
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
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
