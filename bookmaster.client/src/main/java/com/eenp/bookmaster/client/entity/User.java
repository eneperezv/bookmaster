package com.eenp.bookmaster.client.entity;

public class User {
	
	private Integer id;
	private String usuario;
	private String nombre;
	private String clave;
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
