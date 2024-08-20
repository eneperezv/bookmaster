package com.eenp.bookmaster.client.data;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.eenp.bookmaster.client.service.UserSession;

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
