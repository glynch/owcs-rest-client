package io.github.glynch.owcs.rest.client.v1.support;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.glynch.owcs.rest.client.api.exceptions.ErrorHandler;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.client.v1.V1RestClientResponseException;
import io.github.glynch.owcs.rest.client.v1.V1RestError;
import okhttp3.Response;

public class V1ResponseErrorHandler implements ErrorHandler {

    private final ObjectMapper objectMapper;

    public V1ResponseErrorHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handleError(Response response) throws RestClientException {
        try {
            V1RestError error = objectMapper.readValue(response.body().string(),
                    V1RestError.class);
            throw new V1RestClientResponseException(
                    error);
        } catch (IOException e) {
            throw new RestClientException("Failed to parse error response", e);
        }
    }

}
