package io.github.glynch.owcs.rest.client.exceptions;

public class RestClientException extends NestedRuntimeException {

    /**
     * Construct a new instance of {@code RestClientException} with the given
     * message.
     * 
     * @param msg the message
     */
    public RestClientException(String message) {
        super(message);
    }

    /**
     * Construct a new instance of {@code RestClientException} with the given
     * message
     * and exception.
     * 
     * @param msg the message
     * @param ex  the exception
     */
    public RestClientException(String message, Throwable cause) {
        super(message, cause);
    }

}
