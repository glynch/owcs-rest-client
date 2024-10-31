package io.github.glynch.owcs.rest.client.errors;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.glynch.owcs.rest.client.api.exceptions.RuntimeIOException;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.client.v1.V1RestClientResponseException;
import io.github.glynch.owcs.rest.client.v1.errors.V1RestError;
import okhttp3.Response;

public class DefaultResponseErrorHandler implements ResponseErrorHandler {

    private final ObjectMapper objectMapper;

    public DefaultResponseErrorHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handleError(Response response) throws RuntimeIOException {

        try {
            V1RestError error = objectMapper.readValue(response.body().string(), V1RestError.class);
            throw new V1RestClientResponseException(error);
        } catch (IOException e) {
            throw new RestClientException("Failed to parse error response", e);
        }
    }

}
