package com.eenp.bookmaster.client.data;

/*
 * @(#)ApiDataService.java 1.0 07/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Servicio que realiza la conexion con la API
 *
 * @author eliezer.navarro
 * @version 1.0 | 07/08/2024
 * @since 1.0
 */

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ApiDataService {
	
	private final CloseableHttpClient httpClient = HttpClients.createDefault();
	
	public HttpResponse connectToApi(String url, String method, String token, String json) throws URISyntaxException {
        URI uri = new URI(url);
        try {
            HttpUriRequest request = null;
            switch (method) {
                case "GET":
                    request = RequestBuilder.get()
                        .setUri(uri)
    	                .setHeader("Authorization", "Bearer " + token)
                        .build();
                    break;
                case "POST":
                	request = RequestBuilder.post()
    	                .setUri(uri)
    	                .setHeader("Authorization", "Bearer " + token)
    	                .setEntity(new StringEntity(json, ContentType.APPLICATION_JSON))
    	                .build();
                	break;
                case "PUT":
                	request = RequestBuilder.post()
		                .setUri(uri)
		                .setHeader("Authorization", "Bearer " + token)
		                .setEntity(new StringEntity(json, ContentType.APPLICATION_JSON))
		                .build();
                	break;
                default:
                    throw new IllegalArgumentException("Unsupported HTTP method: " + method);
            }
            return httpClient.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
