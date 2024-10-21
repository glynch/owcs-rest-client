package io.github.glynch.owcs.rest.client.exceptions;

public class RestClientResponseException extends RestClientException {

    private static final long serialVersionUID = 1L;

    public RestClientResponseException(String message) {
        super(message);
    }

    public RestClientResponseException(String message, Throwable cause) {
        super(message, cause);
    }

}
