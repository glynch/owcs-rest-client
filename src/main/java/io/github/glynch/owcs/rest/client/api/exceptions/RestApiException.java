package io.github.glynch.owcs.rest.client.api.exceptions;

import io.github.glynch.owcs.rest.client.api.RestError;
import io.github.glynch.owcs.rest.client.exceptions.NestedRuntimeException;

public class RestApiException extends NestedRuntimeException {

    public RestApiException(RestError error) {
        super(error.toString());
    }

    public RestApiException(RestError error, Throwable cause) {
        this(error.toString(), cause);
    }

    public RestApiException(String message) {
        super(message);
    }

    public RestApiException(String message, Throwable cause) {
        super(message, cause);
    }

}
