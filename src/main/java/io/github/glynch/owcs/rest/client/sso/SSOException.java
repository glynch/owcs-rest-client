package io.github.glynch.owcs.rest.client.sso;

import io.github.glynch.owcs.rest.client.exceptions.NestedRuntimeException;

public class SSOException extends NestedRuntimeException {

    public SSOException(String message) {
        super(message);
    }

    public SSOException(String message, Throwable cause) {
        super(message, cause);
    }

}
