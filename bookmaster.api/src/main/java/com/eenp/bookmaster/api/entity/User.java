package com.eenp.bookmaster.api.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="IdUsuario", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="usuario")
	private String usuario;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="clave")
	private String clave;
	
	public User() {
		
	}

	public User(String usuario, String nombre, String clave) {
		super();
		this.usuario = usuario;
		this.nombre = nombre;
		this.clave = clave;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", usuario=" + usuario + ", nombre=" + nombre + ", clave=" + clave + "]";
	}
	
	

}
