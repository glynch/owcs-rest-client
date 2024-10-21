package io.github.glynch.owcs.rest.client.api.exceptions;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestError;
import io.github.glynch.owcs.rest.client.exceptions.NestedRuntimeException;

public class RestApiException extends NestedRuntimeException {

    public RestApiException(AuthenticatedRestError error) {
        super(error.toString());
    }

    public RestApiException(AuthenticatedRestError error, Throwable cause) {
        this(error.toString(), cause);
    }

    public RestApiException(String message) {
        super(message);
    }

    public RestApiException(String message, Throwable cause) {
        super(message, cause);
    }

}
