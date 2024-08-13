package com.eenp.bookmaster.client.entity;

/*
 * @(#)ApiResponse.java 1.0 07/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Entidad para controlar la respuesta de la api
 *
 * @author eliezer.navarro
 * @version 1.0 | 11/08/2024
 * @since 1.0
 */

import org.apache.http.StatusLine;

public class ApiResponse<T> {
    private StatusLine httpResponse;
    private T response;
    private boolean goNext;

    public ApiResponse(StatusLine httpResponse, T response) {
        this.httpResponse = httpResponse;
        this.response = response;
        this.goNext = httpResponse.getStatusCode() == 200 ? true : false;
    }

    public StatusLine getHttpResponse() {
        return httpResponse;
    }

    public T getResponse() {
        return response;
    }

	public boolean isGoNext() {
		return goNext;
	}

	public void setGoNext(boolean goNext) {
		this.goNext = goNext;
	}

	@Override
	public String toString() {
		return "ApiResponse [httpResponse=" + httpResponse.toString() + ", response=" + response.toString() + ", goNext=" + goNext + "]";
	}
}
