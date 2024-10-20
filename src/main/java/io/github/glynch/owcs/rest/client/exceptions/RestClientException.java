package io.github.glynch.owcs.rest.client.exceptions;

public class RestClientException extends NestedRuntimeException {

    public RestClientException(String message) {
        super(message);
    }

    public RestClientException(String message, Throwable cause) {
        super(message, cause);
    }

}
