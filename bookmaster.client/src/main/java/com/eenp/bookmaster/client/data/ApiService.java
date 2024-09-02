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
 * Servicio que llama la conexion con la API
 *
 * @author eliezer.navarro
 * @version 1.0 | 07/08/2024
 * @since 1.0
 */

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpEntity;
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
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.eenp.bookmaster.client.entity.ApiResponse;
import com.eenp.bookmaster.client.entity.Author;
import com.eenp.bookmaster.client.entity.Book;
import com.eenp.bookmaster.client.entity.Client;
import com.eenp.bookmaster.client.entity.ErrorDetails;
import com.eenp.bookmaster.client.entity.Loan;
import com.eenp.bookmaster.client.entity.Publisher;
import com.eenp.bookmaster.client.entity.Token;
import com.eenp.bookmaster.client.entity.User;
import com.eenp.bookmaster.client.service.UserSession;
import com.eenp.bookmaster.client.util.Functions;

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
		HttpResponse response = apiDataService.connectToApi(url,"GET",req.getToken(),"");
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
		HttpResponse response = apiDataService.connectToApi(url,"GET",UserSession.getInstance().getUsuario().getToken(),"");
		if(response.getStatusLine().getStatusCode() == 200) {
        	String responseBody = EntityUtils.toString(response.getEntity());
        	return new ApiResponse<List<User>>(response.getStatusLine(),(List<User>) objectMapper.readValue(responseBody, new TypeReference<List<User>>() {}));
        }else {
        	ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
        	return new ApiResponse<ErrorDetails>(response.getStatusLine(),responseError);
        }
    }
	
	public ApiResponse<?> setUsuarioNuevo(User user) throws URISyntaxException, JsonGenerationException, JsonMappingException, IOException {
		String jsonCliente = objectMapper.writeValueAsString(user);
		String url = URL_API + ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_USER_CREATE);
		HttpResponse response = apiDataService.connectToApi(url,"POST",UserSession.getInstance().getUsuario().getToken(),jsonCliente);
		if (response.getStatusLine().getStatusCode() == 201) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return new ApiResponse<User>(response.getStatusLine(), objectMapper.readValue(responseBody, new TypeReference<User>() {}));
        } else {
            ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
            return new ApiResponse<ErrorDetails>(response.getStatusLine(), responseError);
        }
	}
	
	public ApiResponse<?> setUsuarioUpdate(User user) throws URISyntaxException, JsonGenerationException, JsonMappingException, IOException {
		String jsonCliente = objectMapper.writeValueAsString(user);
		String url = URL_API + ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_USER_UPDATE);
		HttpResponse response = apiDataService.connectToApi(url,"PUT",user.getToken(),jsonCliente);
		if (response.getStatusLine().getStatusCode() == 200) {
			User userAuth = (User) response.getEntity();
			User usuarioNuevo = new User();
			usuarioNuevo.setUsername(userAuth.getUsername());
			usuarioNuevo.setPassword(userAuth.getPassword());
			usuarioNuevo.setName(userAuth.getName());
			usuarioNuevo.setClaveNE(user.getClaveNE());
			ApiResponse<?> leeNuevoToken = getToken(usuarioNuevo);
			if(leeNuevoToken.getHttpResponse().getStatusCode() == 200) {
				Token token = (Token) leeNuevoToken.getResponse();
				usuarioNuevo.setToken(token.getToken());
			}
			System.out.println("USUARIONUEVO->"+usuarioNuevo.toString());
            String responseBody = EntityUtils.toString((HttpEntity) usuarioNuevo);
            return new ApiResponse<User>(response.getStatusLine(), objectMapper.readValue(responseBody, new TypeReference<User>() {}));
        } else {
            ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
            return new ApiResponse<ErrorDetails>(response.getStatusLine(), responseError);
        }
	}
	
	/*
	 * CLIENTES
	 * */
	@SuppressWarnings("unchecked")
	public ApiResponse<?> getClientes() throws URISyntaxException, ParseException, IOException {
		String url = URL_API + ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_CLIENTES_TODOS);
		HttpResponse response = apiDataService.connectToApi(url,"GET",UserSession.getInstance().getUsuario().getToken(),"");
		if(response.getStatusLine().getStatusCode() == 200) {
        	String responseBody = EntityUtils.toString(response.getEntity());
        	return new ApiResponse<List<Client>>(response.getStatusLine(),(List<Client>) objectMapper.readValue(responseBody, new TypeReference<List<Client>>() {}));
        }else {
        	ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
        	return new ApiResponse<ErrorDetails>(response.getStatusLine(),responseError);
        }
    }
	
	public ApiResponse<?> setClienteNuevo(Client cliente) throws URISyntaxException, JsonGenerationException, JsonMappingException, IOException {
		String jsonCliente = objectMapper.writeValueAsString(cliente);
		String url = URL_API + ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_CLIENTES_CREATE);
		HttpResponse response = apiDataService.connectToApi(url,"POST",UserSession.getInstance().getUsuario().getToken(),jsonCliente);
		if (response.getStatusLine().getStatusCode() == 200) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return new ApiResponse<Client>(response.getStatusLine(), objectMapper.readValue(responseBody, new TypeReference<Client>() {}));
        } else {
            ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
            return new ApiResponse<ErrorDetails>(response.getStatusLine(), responseError);
        }
	}

	@SuppressWarnings("unchecked")
	public ApiResponse<?> findClienteByNombre(String nombre) throws URISyntaxException, ParseException, IOException {
		String url = URL_API + ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_CLIENTES_BYNOMBRE) + nombre;
		HttpResponse response = apiDataService.connectToApi(url,"GET",UserSession.getInstance().getUsuario().getToken(),"");
		if(response.getStatusLine().getStatusCode() == 200) {
        	String responseBody = EntityUtils.toString(response.getEntity());
        	return new ApiResponse<List<Client>>(response.getStatusLine(),(List<Client>) objectMapper.readValue(responseBody, new TypeReference<List<Client>>() {}));
        }else {
        	ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
        	return new ApiResponse<ErrorDetails>(response.getStatusLine(),responseError);
        }
	}
	
	/*
	 * AUTORES
	 * */
	@SuppressWarnings("unchecked")
	public ApiResponse<?> getAutores() throws URISyntaxException, ParseException, IOException {
		String url = URL_API + ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_AUTORES_TODOS);
		HttpResponse response = apiDataService.connectToApi(url,"GET",UserSession.getInstance().getUsuario().getToken(),"");
		if(response.getStatusLine().getStatusCode() == 200) {
        	String responseBody = EntityUtils.toString(response.getEntity());
        	return new ApiResponse<List<Author>>(response.getStatusLine(),(List<Author>) objectMapper.readValue(responseBody, new TypeReference<List<Author>>() {}));
        }else {
        	ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
        	return new ApiResponse<ErrorDetails>(response.getStatusLine(),responseError);
        }
	}

	public ApiResponse<?> setAutorNuevo(Author author) throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
		String jsonCliente = objectMapper.writeValueAsString(author);
		String url = URL_API + ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_AUTORES_CREATE);
		HttpResponse response = apiDataService.connectToApi(url,"POST",UserSession.getInstance().getUsuario().getToken(),jsonCliente);
		if (response.getStatusLine().getStatusCode() == 200) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return new ApiResponse<Author>(response.getStatusLine(), objectMapper.readValue(responseBody, new TypeReference<Author>() {}));
        } else {
            ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
            return new ApiResponse<ErrorDetails>(response.getStatusLine(), responseError);
        }
	}
	
	/*
	 * EDITORIALES
	 * */
	@SuppressWarnings("unchecked")
	public ApiResponse<?> getEditoriales() throws URISyntaxException, ParseException, IOException {
		String url = URL_API + ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_PUBLISHER_TODOS);
		HttpResponse response = apiDataService.connectToApi(url,"GET",UserSession.getInstance().getUsuario().getToken(),"");
		if(response.getStatusLine().getStatusCode() == 200) {
        	String responseBody = EntityUtils.toString(response.getEntity());
        	return new ApiResponse<List<Publisher>>(response.getStatusLine(),(List<Publisher>) objectMapper.readValue(responseBody, new TypeReference<List<Publisher>>() {}));
        }else {
        	ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
        	return new ApiResponse<ErrorDetails>(response.getStatusLine(),responseError);
        }
	}

	public ApiResponse<?> setEditorialNuevo(Publisher publisher) throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
		String jsonCliente = objectMapper.writeValueAsString(publisher);
		String url = URL_API + ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_PUBLISHER_CREATE);
		HttpResponse response = apiDataService.connectToApi(url,"POST",UserSession.getInstance().getUsuario().getToken(),jsonCliente);
		if (response.getStatusLine().getStatusCode() == 200) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return new ApiResponse<Publisher>(response.getStatusLine(), objectMapper.readValue(responseBody, new TypeReference<Publisher>() {}));
        } else {
            ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
            return new ApiResponse<ErrorDetails>(response.getStatusLine(), responseError);
        }
	}
	
	/*
	 * LIBROS
	 * */
	@SuppressWarnings("unchecked")
	public ApiResponse<?> getLibros() throws URISyntaxException, ParseException, IOException {
		String url = URL_API + ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_BOOK_TODOS);
		HttpResponse response = apiDataService.connectToApi(url,"GET",UserSession.getInstance().getUsuario().getToken(),"");
		if(response.getStatusLine().getStatusCode() == 200) {
        	String responseBody = EntityUtils.toString(response.getEntity());
        	return new ApiResponse<List<Book>>(response.getStatusLine(),(List<Book>) objectMapper.readValue(responseBody, new TypeReference<List<Book>>() {}));
        }else {
        	ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
        	return new ApiResponse<ErrorDetails>(response.getStatusLine(),responseError);
        }
	}

	public ApiResponse<?> setLibroNuevo(Book book) throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
		String jsonCliente = objectMapper.writeValueAsString(book);
		String url = URL_API + ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_BOOK_CREATE);
		HttpResponse response = apiDataService.connectToApi(url,"POST",UserSession.getInstance().getUsuario().getToken(),jsonCliente);
		if (response.getStatusLine().getStatusCode() == 200) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return new ApiResponse<Book>(response.getStatusLine(), objectMapper.readValue(responseBody, new TypeReference<Book>() {}));
        } else {
            ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
            return new ApiResponse<ErrorDetails>(response.getStatusLine(), responseError);
        }
	}

	@SuppressWarnings("unchecked")
	public ApiResponse<?> findByAuthor(String author) throws URISyntaxException, ParseException, IOException {
		String url = URL_API + ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_BOOK_BYAUTHOR) + author;
		HttpResponse response = apiDataService.connectToApi(url,"GET",UserSession.getInstance().getUsuario().getToken(),"");
		if(response.getStatusLine().getStatusCode() == 200) {
        	String responseBody = EntityUtils.toString(response.getEntity());
        	return new ApiResponse<List<Book>>(response.getStatusLine(),(List<Book>) objectMapper.readValue(responseBody, new TypeReference<List<Book>>() {}));
        }else {
        	ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
        	return new ApiResponse<ErrorDetails>(response.getStatusLine(),responseError);
        }
	}

	@SuppressWarnings("unchecked")
	public ApiResponse<?> findByTitle(String title) throws URISyntaxException, ParseException, IOException {
		String url = URL_API + ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_BOOK_BYTITLE) + title;
		HttpResponse response = apiDataService.connectToApi(url,"GET",UserSession.getInstance().getUsuario().getToken(),"");
		if(response.getStatusLine().getStatusCode() == 200) {
        	String responseBody = EntityUtils.toString(response.getEntity());
        	return new ApiResponse<List<Book>>(response.getStatusLine(),(List<Book>) objectMapper.readValue(responseBody, new TypeReference<List<Book>>() {}));
        }else {
        	ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
        	return new ApiResponse<ErrorDetails>(response.getStatusLine(),responseError);
        }
	}
	
	/*
	 * PRESTAMOS
	 * */
	@SuppressWarnings("unchecked")
	public ApiResponse<?> getPrestamos() throws URISyntaxException, ParseException, IOException {
		String url = URL_API + ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_LOAN_TODOS);
		HttpResponse response = apiDataService.connectToApi(url,"GET",UserSession.getInstance().getUsuario().getToken(),"");
		if(response.getStatusLine().getStatusCode() == 200) {
        	String responseBody = EntityUtils.toString(response.getEntity());
        	return new ApiResponse<List<Loan>>(response.getStatusLine(),(List<Loan>) objectMapper.readValue(responseBody, new TypeReference<List<Loan>>() {}));
        }else {
        	ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
        	return new ApiResponse<ErrorDetails>(response.getStatusLine(),responseError);
        }
	}

	public ApiResponse<?> setPrestamoNuevo(Loan loan) throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
		String jsonCliente = objectMapper.writeValueAsString(loan);
		String url = URL_API + ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_LOAN_CREATE);
		HttpResponse response = apiDataService.connectToApi(url,"POST",UserSession.getInstance().getUsuario().getToken(),jsonCliente);
		if (response.getStatusLine().getStatusCode() == 200) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return new ApiResponse<Loan>(response.getStatusLine(), objectMapper.readValue(responseBody, new TypeReference<Loan>() {}));
        } else {
            ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
            return new ApiResponse<ErrorDetails>(response.getStatusLine(), responseError);
        }
	}

	public ApiResponse<?> getPrestamosByCliente(String nombre) throws URISyntaxException, ParseException, IOException {
		String url = URL_API + ApiServiceConfig.obtenerInstancia().obtenerValor(ApiServiceConstants.ENDPOINT_LOAN_BYCLIENT);
		HttpResponse response = apiDataService.connectToApi(url,"GET",UserSession.getInstance().getUsuario().getToken(),"");
		if(response.getStatusLine().getStatusCode() == 200) {
        	String responseBody = EntityUtils.toString(response.getEntity());
        	return new ApiResponse<List<Loan>>(response.getStatusLine(),(List<Loan>) objectMapper.readValue(responseBody, new TypeReference<List<Loan>>() {}));
        }else {
        	ErrorDetails responseError = func.obtenerRespuestaError(response.getStatusLine());
        	return new ApiResponse<ErrorDetails>(response.getStatusLine(),responseError);
        }
	}
}
