package io.github.glynch.owcs.rest.client.authenticated;

import java.nio.charset.Charset;

import io.github.glynch.owcs.rest.client.RestClientResponseException;

public class AuthenticatedRestClientResponseException extends RestClientResponseException {

    private final AuthenticatedRestError error;

    public AuthenticatedRestClientResponseException(AuthenticatedRestError error, int statusCode, String statusText, byte[] responseBody, Charset charset) {
        super(error.getMessage(), statusCode, statusText, responseBody, charset);
        this.error = error;
    }

    public AuthenticatedRestError getError() {
        return error;
    }

}
