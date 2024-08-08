package com.eenp.bookmaster.api.entity;

public class User {
	
	private String usuario;
	private String nombre;
	private String clave;

	public User(String usuario, String nombre, String clave) {
		super();
		this.usuario = usuario;
		this.nombre = nombre;
		this.clave = clave;
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
		return "User [usuario=" + usuario + ", nombre=" + nombre + ", clave=" + clave + "]";
	}

}
