package com.eenp.bookmaster.client.util;

/*
 * @(#)Main.java 1.0 08/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html

incluir documentacion adicional
 */

/**
 * Biblioteca de funciones comunes para toda la aplicación
 *
 * @author eliezer.navarro
 * @version 1.0 | 08/08/2024
 * @since 1.0
 */

import java.security.MessageDigest;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.http.StatusLine;
import org.mindrot.jbcrypt.BCrypt;

import com.eenp.bookmaster.client.entity.ErrorDetails;

public class Functions {
	
	public String retornaMD5(String str){
        String valor = "";
        
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            valor = sb.toString();
        }catch(Exception e){
        }
        
        return valor;
    }
	
	public void showMSG(String OPC,String TEXTO,String TITULO){
        if(OPC.equals("ERROR")){
            JOptionPane.showMessageDialog(null,TEXTO,TITULO,JOptionPane.ERROR_MESSAGE);
        }
        if(OPC.equals("OK")){
            JOptionPane.showMessageDialog(null,TEXTO,TITULO,JOptionPane.INFORMATION_MESSAGE);
        }
    }
	
	public String retornaHashBCrypt(String str) {
		int workload = 10;
		
		String salt = BCrypt.gensalt(workload);

		return BCrypt.hashpw(str, salt);
	}
	
	public boolean checkPassword(String password_plaintext, String stored_hash) {
		boolean password_verified = false;

		if(null == stored_hash || !stored_hash.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

		return password_verified;
	}
	
	public ErrorDetails obtenerRespuestaError(StatusLine respuesta) {
		Date fecha = new Date();
		String mensaje = "";
		
		if(respuesta.getStatusCode() ==100) { mensaje = "Continue\nContinuar con la solicitud o ignorar si ya está completa.";
		}else if(respuesta.getStatusCode() ==101) { mensaje = "Switching Protocols\nEl servidor cambia de protocolo según la solicitud Upgrade.";
		}else if(respuesta.getStatusCode() ==200) { mensaje = "OK\nLa solicitud se completó con éxito.";
		}else if(respuesta.getStatusCode() ==201) { mensaje = "Created\nSe creó un nuevo recurso como resultado de la solicitud.";
		}else if(respuesta.getStatusCode() ==202) { mensaje = "Accepted\nLa solicitud se recibió pero aún no se ha procesado.";
		}else if(respuesta.getStatusCode() ==204) { mensaje = "No Content\nNo hay contenido para enviar, pero las cabeceras pueden ser útiles.";
		}else if(respuesta.getStatusCode() ==300) { mensaje = "Multiple Choices\nMúltiples opciones disponibles para redirección.";
		}else if(respuesta.getStatusCode() ==301) { mensaje = "Moved Permanently\nEl recurso solicitado se ha movido permanentemente.";
		}else if(respuesta.getStatusCode() ==400) { mensaje = "Bad Request\nLa solicitud es incorrecta o mal formada.";
		}else if(respuesta.getStatusCode() ==401) { mensaje = "Unauthorized\nSe requiere autenticación para acceder al recurso.\nVerifique si su clave es correcta.";
		}else if(respuesta.getStatusCode() ==403) { mensaje = "Forbidden\nEl cliente no tiene permiso para acceder al recurso.";
		}else if(respuesta.getStatusCode() ==404) { mensaje = "Not Found\nEl recurso solicitado no existe en el servidor.";
		}else if(respuesta.getStatusCode() ==405) { mensaje = "Method Not Allowed\nEl método de solicitud no está permitido para el recurso.";
		}else if(respuesta.getStatusCode() ==500) { mensaje = "Internal Server Error\nError interno del servidor.";
		}else { mensaje = "Error no Controlado. Comuniquese con el administrador del sistema."; }
		
		return new ErrorDetails(fecha,Integer.toString(respuesta.getStatusCode()),mensaje);
	}

}
