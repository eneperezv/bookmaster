package com.eenp.bookmaster.api.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Clients")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="IdCliente", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="correoelectronico")
	private String correoelectronico;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="telefono")
	private String telefono;
	
	public Client() {
		
	}

	public Client(String nombre, String correoelectronico, String direccion, String telefono) {
		super();
		this.nombre = nombre;
		this.correoelectronico = correoelectronico;
		this.direccion = direccion;
		this.telefono = telefono;
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
		return "Clients [id=" + id + ", nombre=" + nombre + ", correoelectronico=" + correoelectronico + ", direccion="
				+ direccion + ", telefono=" + telefono + "]";
	}

}
