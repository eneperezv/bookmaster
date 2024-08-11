package com.eenp.bookmaster.client.util;

import java.security.MessageDigest;

import javax.swing.JOptionPane;

import org.mindrot.jbcrypt.BCrypt;

/*
 * @(#)Main.java 1.0 08/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Biblioteca de funciones comunes para toda la aplicación
 *
 * @author eliezer.navarro
 * @version 1.0 | 08/08/2024
 * @since 1.0
 */

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

}
