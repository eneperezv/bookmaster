package com.eenp.bookmaster.client.data;

import java.io.IOException;

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
import org.apache.http.ParseException;
import org.apache.http.auth.UsernamePasswordCredentials;
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
import com.eenp.bookmaster.client.entity.Author;
import com.eenp.bookmaster.client.entity.Client;
import com.eenp.bookmaster.client.entity.ErrorDetails;
import com.eenp.bookmaster.client.entity.Publisher;
import com.eenp.bookmaster.client.entity.Token;
import com.eenp.bookmaster.client.entity.User;
import com.eenp.bookmaster.client.service.UserSession;
import com.eenp.bookmaster.client.util.Functions;
import com.eenp.bookmaster.client.data.ApiDataService;

public class ApiService {
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final String URL_API = ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.API_URL);
	private final ApiDataService apiDataService;
	
	Functions func = new Functions();
	
	ApiServiceConfig config;
	
	public ApiService() {
		this.apiDataService = new ApiDataService();
	}
	
	/*
	 * LOGIN
	 * */
	public ApiResponse<?> getToken(User user) throws URISyntaxException {
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

	        String apiUrl = ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.API_URL);
	        String endpointGetToken = ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_TOKEN);
	        String url = apiUrl + endpointGetToken;
	        URI uri = new URI(url);

	        String jsonCliente = objectMapper.writeValueAsString(user);

	        HttpUriRequest request = RequestBuilder.post()
	                .setUri(uri)
	                .setHeader(new BasicScheme().authenticate(
	                        new UsernamePasswordCredentials(user.getUsername(),user.getPassword()),
	                        new HttpPost(uri), null))
	                .setEntity(new StringEntity(jsonCliente, ContentType.APPLICATION_JSON))
	                .build();

	        HttpResponse response = httpClient.execute(request);

	        if (response.getStatusLine().getStatusCode() == 200) {
	            String responseBody = EntityUtils.toString(response.getEntity());
	            return new ApiResponse<Token>(response.getStatusLine(), objectMapper.readValue(responseBody, new TypeReference<Token>() {}));
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
	 * USUARIOS
	 * */
	public ApiResponse<?> getDatosUsuario(User req) throws URISyntaxException, ParseException, IOException {
		String url = URL_API + ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_USER) + req.getUsername();
		HttpResponse response = apiDataService.connectToApi(url,"GET",req.getToken());
		if(response.getStatusLine().getStatusCode() == 200) {
        	String responseBody = EntityUtils.toString(response.getEntity());
            return new ApiResponse<User>(response.getStatusLine(), objectMapper.readValue(responseBody, User.class));
        }else {
        	ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
        	return new ApiResponse<ErrorDetails>(response.getStatusLine(),responseError);
        }
	}
	
	@SuppressWarnings("unchecked")
	public ApiResponse<?> getUsuarios() throws URISyntaxException, ParseException, IOException {
		String url = URL_API + ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_USER_TODOS);
		HttpResponse response = apiDataService.connectToApi(url,"GET",UserSession.getInstance().getUsuario().getToken());
		if(response.getStatusLine().getStatusCode() == 200) {
        	String responseBody = EntityUtils.toString(response.getEntity());
        	return new ApiResponse<List<User>>(response.getStatusLine(),(List<User>) objectMapper.readValue(responseBody, new TypeReference<List<User>>() {}));
        }else {
        	ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
        	return new ApiResponse<ErrorDetails>(response.getStatusLine(),responseError);
        }
    }
	
	public ApiResponse<?> setUsuarioNuevo(User user) throws URISyntaxException {
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

	        String apiUrl = ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.API_URL);
	        String endpointClientesCreate = ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_USER_CREATE);
	        String url = apiUrl + endpointClientesCreate;
	        URI uri = new URI(url);

	        String jsonCliente = objectMapper.writeValueAsString(user);

	        HttpUriRequest request = RequestBuilder.post()
	                .setUri(uri)
	                .setHeader("Authorization", "Bearer " + UserSession.getInstance().getUsuario().getToken())
	                .setEntity(new StringEntity(jsonCliente, ContentType.APPLICATION_JSON))
	                .build();

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
	
	public ApiResponse<?> setUsuarioUpdate(User user) throws URISyntaxException {
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

	        String apiUrl = ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.API_URL);
	        String endpointClientesCreate = ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_USER_UPDATE);
	        String url = apiUrl + endpointClientesCreate;
	        URI uri = new URI(url);

	        String jsonCliente = objectMapper.writeValueAsString(user);

	        HttpUriRequest request = RequestBuilder.post()
	                .setUri(uri)
	                .setHeader("Authorization", "Bearer " + UserSession.getInstance().getUsuario().getToken())
	                .setEntity(new StringEntity(jsonCliente, ContentType.APPLICATION_JSON))
	                .build();

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
	 * CLIENTES
	 * */
	@SuppressWarnings("unchecked")
	public ApiResponse<?> getClientes() throws URISyntaxException, ParseException, IOException {
		String url = URL_API + ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_CLIENTES_TODOS);
		HttpResponse response = apiDataService.connectToApi(url,"GET",UserSession.getInstance().getUsuario().getToken());
		if(response.getStatusLine().getStatusCode() == 200) {
        	String responseBody = EntityUtils.toString(response.getEntity());
        	return new ApiResponse<List<Client>>(response.getStatusLine(),(List<Client>) objectMapper.readValue(responseBody, new TypeReference<List<Client>>() {}));
        }else {
        	ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
        	return new ApiResponse<ErrorDetails>(response.getStatusLine(),responseError);
        }
    }
	
	public ApiResponse<?> setClienteNuevo(Client cliente) throws URISyntaxException {
	    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

	        String apiUrl = ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.API_URL);
	        String endpointClientesCreate = ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_CLIENTES_CREATE);
	        String url = apiUrl + endpointClientesCreate;
	        URI uri = new URI(url);

	        String jsonCliente = objectMapper.writeValueAsString(cliente);

	        HttpUriRequest request = RequestBuilder.post()
	                .setUri(uri)
	                .setHeader("Authorization", "Bearer " + UserSession.getInstance().getUsuario().getToken())
	                .setEntity(new StringEntity(jsonCliente, ContentType.APPLICATION_JSON))
	                .build();

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
	 * AUTORES
	 * */
	@SuppressWarnings("unchecked")
	public ApiResponse<?> getAutores() throws URISyntaxException, ParseException, IOException {
		String url = URL_API + ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_AUTORES_TODOS);
		HttpResponse response = apiDataService.connectToApi(url,"GET",UserSession.getInstance().getUsuario().getToken());
		if(response.getStatusLine().getStatusCode() == 200) {
        	String responseBody = EntityUtils.toString(response.getEntity());
        	return new ApiResponse<List<Author>>(response.getStatusLine(),(List<Author>) objectMapper.readValue(responseBody, new TypeReference<List<Author>>() {}));
        }else {
        	ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
        	return new ApiResponse<ErrorDetails>(response.getStatusLine(),responseError);
        }
	}
	
	/*
	 * EDITORIALES
	 * */
	@SuppressWarnings("unchecked")
	public ApiResponse<?> getEditoriales() throws URISyntaxException, ParseException, IOException {
		String url = URL_API + ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_PUBLISHER_TODOS);
		HttpResponse response = apiDataService.connectToApi(url,"GET",UserSession.getInstance().getUsuario().getToken());
		if(response.getStatusLine().getStatusCode() == 200) {
        	String responseBody = EntityUtils.toString(response.getEntity());
        	return new ApiResponse<List<Publisher>>(response.getStatusLine(),(List<Publisher>) objectMapper.readValue(responseBody, new TypeReference<List<Publisher>>() {}));
        }else {
        	ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
        	return new ApiResponse<ErrorDetails>(response.getStatusLine(),responseError);
        }
	}
}
