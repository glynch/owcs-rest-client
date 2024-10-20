package io.github.glynch.owcs.rest.client.exceptions;

import io.github.glynch.owcs.rest.client.api.V1RestError;

public class RestClientRequestException extends RestClientException {

    private static final long serialVersionUID = 1L;

    private final V1RestError error;

    public RestClientRequestException(V1RestError error) {
        super(error.toString());
        this.error = error;
    }

    public int getHttpStatus() {
        return error.httpStatus();
    }

    public String getProblemInstance() {
        return error.problemInstance();
    }

    public String getDetail() {
        return error.detail();
    }

    public String getTitle() {
        return error.title();
    }

    public V1RestError getError() {
        return error;
    }

}
