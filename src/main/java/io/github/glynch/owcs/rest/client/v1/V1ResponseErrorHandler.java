package io.github.glynch.owcs.rest.client.v1;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.glynch.owcs.rest.client.api.exceptions.ErrorHandler;
import io.github.glynch.owcs.rest.client.api.exceptions.RestApiException;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestError;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import okhttp3.Response;

public class V1ResponseErrorHandler implements ErrorHandler {

    private final ObjectMapper objectMapper;

    public V1ResponseErrorHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handleError(Response response) throws RestClientException {
        try {
            AuthenticatedRestError error = objectMapper.readValue(response.body().string(),
                    AuthenticatedRestError.class);
            throw new RestApiException(
                    error);
        } catch (IOException e) {
            throw new RestApiException("Failed to parse error response", e);
        }
    }

}
