package io.github.glynch.owcs.rest.client.authenticated;

import io.github.glynch.owcs.rest.client.exceptions.RestClientResponseException;

public class AuthenticatedRestClientResponseException extends RestClientResponseException {

    private static final long serialVersionUID = 1L;

    private final AuthenticatedRestError error;

    public AuthenticatedRestClientResponseException(AuthenticatedRestError error) {
        super(error.message());
        this.error = error;
    }

    public int getErrorCode() {
        return error.errorCode();
    }

}
