package com.eenp.bookmaster.client.data;

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
