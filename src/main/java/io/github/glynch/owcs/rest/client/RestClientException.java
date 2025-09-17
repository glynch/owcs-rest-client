package io.github.glynch.owcs.rest.client;

import org.springframework.core.NestedRuntimeException;

public class RestClientException extends NestedRuntimeException {

    public RestClientException(String msg) {
        super(msg);
    }

    public RestClientException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
