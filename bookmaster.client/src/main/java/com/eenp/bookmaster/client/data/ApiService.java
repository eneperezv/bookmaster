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
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.eenp.bookmaster.client.entity.ApiResponse;
import com.eenp.bookmaster.client.entity.Client;
import com.eenp.bookmaster.client.entity.ErrorDetails;
import com.eenp.bookmaster.client.entity.User;
import com.eenp.bookmaster.client.service.UserSession;
import com.eenp.bookmaster.client.util.Functions;

public class ApiService {
	//ORGANIZAR ESTAS CONSTANTES EN UNA CLASE APARTE
	private static final String API_URL                  = "API_URL";
	private static final String ENDPOINT_USER            = "ENDPOINT_USER";
	private static final String ENDPOINT_CLIENTES_TODOS  = "ENDPOINT_CLIENTES_TODOS";
	private static final String ENDPOINT_CLIENTES_CREATE = "ENDPOINT_CLIENTES_CREATE";
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	Functions func = new Functions();
	
	ApiServiceConfig config;

	public ApiResponse<?> getDatosUsuario(String usuario,String clave) throws URISyntaxException {

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
            	return new ApiResponse<User>(response.getStatusLine(),objectMapper.readValue(responseBody, User.class));
            }else {
            	ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
            	return new ApiResponse<ErrorDetails>(response.getStatusLine(),responseError);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	public ApiResponse<?> getClientes() throws URISyntaxException {

		config = ApiServiceConfig.obtenerInstancia();
		
		String url = config.obtenerValor(API_URL) + config.obtenerValor(ENDPOINT_CLIENTES_TODOS);
        URI uri = new URI(url);
        
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpUriRequest request = RequestBuilder.get()
                    .setUri(uri)
                    .setHeader(new BasicScheme().authenticate(
                            new UsernamePasswordCredentials(UserSession.getInstance().getUsuario().getUsuario(), UserSession.getInstance().getUsuario().getClaveNE()),
                            new HttpGet(uri), null))
                    .build();
            HttpResponse response = httpClient.execute(request);
            
            if(response.getStatusLine().getStatusCode() == 200) {
            	String responseBody = EntityUtils.toString(response.getEntity());
            	return new ApiResponse<List<Client>>(response.getStatusLine(),(List<Client>) objectMapper.readValue(responseBody, new TypeReference<List<Client>>() {}));
            }else {
            	ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
            	return new ApiResponse<ErrorDetails>(response.getStatusLine(),responseError);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public ApiResponse<?> setClienteNuevo(Client cliente) throws URISyntaxException {
	    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
	        // Configuración de la URL y la autenticación
	        String apiUrl = ApiServiceConfig.obtenerInstancia().obtenerValor(API_URL);
	        String endpointClientesCreate = ApiServiceConfig.obtenerInstancia().obtenerValor(ENDPOINT_CLIENTES_CREATE);
	        String url = apiUrl + endpointClientesCreate;
	        URI uri = new URI(url);

	        // Crear objeto JSON con los datos del cliente
	        String jsonCliente = objectMapper.writeValueAsString(cliente);

	        // Crear solicitud HTTP POST
	        HttpUriRequest request = RequestBuilder.post()
	                .setUri(uri)
	                .setHeader(new BasicScheme().authenticate(
	                        new UsernamePasswordCredentials(UserSession.getInstance().getUsuario().getUsuario(), UserSession.getInstance().getUsuario().getClaveNE()),
	                        new HttpPost(uri), null))
	                .setEntity(new StringEntity(jsonCliente, ContentType.APPLICATION_JSON))
	                .build();

	        // Ejecutar la solicitud
	        HttpResponse response = httpClient.execute(request);

	        if (response.getStatusLine().getStatusCode() == 200) {
	            String responseBody = EntityUtils.toString(response.getEntity());
	            return new ApiResponse<Client>(response.getStatusLine(), objectMapper.readValue(responseBody, new TypeReference<Client>() {}));
	        } else {
	            ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
	            return new ApiResponse<ErrorDetails>(response.getStatusLine(), responseError);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	
	
	
	
	/*
	public ApiResponse<?> setClienteNuevo(Client cliente) throws URISyntaxException{
		
		config = ApiServiceConfig.obtenerInstancia();
		
		String url = config.obtenerValor(API_URL) + config.obtenerValor(ENDPOINT_CLIENTES_CREATE);
        URI uri = new URI(url);
        
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpUriRequest request = RequestBuilder.get()
                    .setUri(uri)
                    .setHeader(new BasicScheme().authenticate(
                            new UsernamePasswordCredentials(UserSession.getInstance().getUsuario().getUsuario(), UserSession.getInstance().getUsuario().getClaveNE()),
                            new HttpGet(uri), null))
                    .build();
            HttpResponse response = httpClient.execute(request);
            
            if(response.getStatusLine().getStatusCode() == 200) {
            	String responseBody = EntityUtils.toString(response.getEntity());
            	return new ApiResponse<List<Client>>(response.getStatusLine(),(List<Client>) objectMapper.readValue(responseBody, new TypeReference<List<Client>>() {}));
            }else {
            	ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
            	return new ApiResponse<ErrorDetails>(response.getStatusLine(),responseError);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
		
	}
	*/
}
