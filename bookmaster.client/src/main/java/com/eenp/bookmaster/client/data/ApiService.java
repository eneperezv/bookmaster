package com.eenp.bookmaster.client.data;

import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import com.eenp.bookmaster.client.entity.User;

public class ApiService {

	private static final String API_URL       = "API_URL";
	private static final String ENDPOINT_USER = "ENDPOINT_USER";
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	ApiServiceConfig config;
	
	public User getDatosUsuario(String usuario) throws URISyntaxException {
		config = ApiServiceConfig.obtenerInstancia();
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(config.obtenerValor(API_URL) + 
            							  config.obtenerValor(ENDPOINT_USER) + 
            							  usuario);
            HttpResponse response = httpClient.execute(httpGet);
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
