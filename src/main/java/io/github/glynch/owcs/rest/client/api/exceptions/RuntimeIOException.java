package io.github.glynch.owcs.rest.client.api.exceptions;

import java.io.IOException;

import io.github.glynch.owcs.rest.client.exceptions.NestedRuntimeException;

public class RuntimeIOException extends NestedRuntimeException {

    public RuntimeIOException(String message) {
        super(message);
    }

    public RuntimeIOException(String message, IOException cause) {
        super(message, cause);
    }

    public RuntimeIOException(IOException cause) {
        super(cause);
    }

}
