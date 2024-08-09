package com.eenp.bookmaster.client.data;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import com.eenp.bookmaster.client.entity.User;

public class ApiService {
	
	private static final String API_URL = "http://localhost:8080/api/bookmaster/user";
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	public User getDatosUsuario(String usuario) {
		System.out.println("ENTRO A ApiService");
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(API_URL + "/" + usuario);
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
