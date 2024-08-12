package com.eenp.bookmaster.client.service;

/*
 * @(#)UserSession.java 1.0 07/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/** Capa de Registro global de Control de Usuarios
 *
 * @author eliezer.navarro
 * @version 1.0 | 07/08/2024
 * @since 1.0
 */

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
