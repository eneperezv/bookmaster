package com.eenp.bookmaster.client.service;

import com.eenp.bookmaster.client.entity.User;

public final class UserSession {
	
	private static UserSession instance;
	
    private User usuario;
    
    private UserSession() {
    	
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "UserSession [usuario=" + usuario.toString() + "]";
	}

}
