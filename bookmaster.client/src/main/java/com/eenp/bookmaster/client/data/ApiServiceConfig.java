package com.eenp.bookmaster.client.data;

/*
 * @(#)ApiServiceConfig.java 1.0 07/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Clase Singleton para leer el archivo properties de configuraciones de la API
 *
 * @author eliezer.navarro
 * @version 1.0 | 07/08/2024
 * @since 1.0
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class ApiServiceConfig {
	
	private static ApiServiceConfig instancia;
    private Properties properties;
    
    private ApiServiceConfig() throws URISyntaxException {
    	File miDir = new File (".");
        properties = new Properties();
        try {
        	System.out.println("LEE EL ARCHIVO PROPERTIES");
            properties.load(new FileInputStream(new File(miDir.getCanonicalPath() + "\\src\\main\\java\\com\\eenp\\bookmaster\\client\\resources\\apiconfig.properties")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static ApiServiceConfig obtenerInstancia() throws URISyntaxException {
        if (instancia == null) {
            instancia = new ApiServiceConfig();
        }
        return instancia;
    }

    public String obtenerValor(String clave) {
        return properties.getProperty(clave);
    }

}
