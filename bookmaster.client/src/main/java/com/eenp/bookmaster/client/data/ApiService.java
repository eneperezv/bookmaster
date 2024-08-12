package com.eenp.bookmaster.client.data;

/*
 * @(#)ApiService.java 1.0 07/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Servicio que conecta con la API
 *
 * @author eliezer.navarro
 * @version 1.0 | 07/08/2024
 * @since 1.0
 */

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.eenp.bookmaster.client.entity.User;

public class ApiService {

	private static final String API_URL       = "API_URL";
	private static final String ENDPOINT_USER = "ENDPOINT_USER";
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	ApiServiceConfig config;
	//IMPLEMENTAR RESPUESTAS Y POSIBLE USO DE RESPONSEENTITY
	//TODOS LOS ESCENARIOS DE RESPUESTA HTTP DEBEN EMITIR UN MENSAJE ErrorDetails
	public User getDatosUsuario(String usuario,String clave) throws URISyntaxException {
        
		config = ApiServiceConfig.obtenerInstancia();
		
		String url = config.obtenerValor(API_URL) + config.obtenerValor(ENDPOINT_USER) + usuario;
        URI uri = new URI(url);
        
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpUriRequest request = RequestBuilder.get()
                    .setUri(uri)
                    .setHeader(new BasicScheme().authenticate(
                            new UsernamePasswordCredentials(usuario, clave),
                            new HttpGet(uri), null))
                    .build();
            HttpResponse response = httpClient.execute(request);

            if(response.getStatusLine().getStatusCode() == 200) {
            	String responseBody = EntityUtils.toString(response.getEntity());
            	return objectMapper.readValue(responseBody, User.class);
            }else {
            	return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
