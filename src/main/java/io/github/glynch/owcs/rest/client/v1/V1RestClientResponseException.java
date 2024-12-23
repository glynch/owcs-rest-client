package io.github.glynch.owcs.rest.client.v1;

import io.github.glynch.owcs.rest.client.exceptions.RestClientResponseException;
import io.github.glynch.owcs.rest.client.v1.errors.V1RestError;

public class V1RestClientResponseException extends RestClientResponseException {

    private static final long serialVersionUID = 1L;

    private final V1RestError error;

    public V1RestClientResponseException(V1RestError error) {
        super(error.detail());
        this.error = error;
    }

    public int getHttpStatus() {
        return error.httpStatus();
    }

    public String getTitle() {
        return error.title();
    }

    public String getDetail() {
        return error.detail();
    }

    public String getProblemType() {
        return error.problemType();
    }

}
