package com.eenp.bookmaster.client.entity;

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
