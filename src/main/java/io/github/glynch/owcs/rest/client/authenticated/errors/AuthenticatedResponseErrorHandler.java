package io.github.glynch.owcs.rest.client.authenticated.errors;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClientResponseException;
import io.github.glynch.owcs.rest.client.errors.ResponseErrorHandler;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.client.exceptions.RestClientResponseException;
import okhttp3.Response;

public class AuthenticatedResponseErrorHandler implements ResponseErrorHandler {

    private final ObjectMapper objectMapper;

    public AuthenticatedResponseErrorHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handleError(Response response) throws RestClientResponseException {
        try {
            AuthenticatedRestError error = objectMapper.readValue(response.body().string(),
                    AuthenticatedRestError.class);
            throw new AuthenticatedRestClientResponseException(error);
        } catch (IOException e) {
            throw new RestClientException("Failed to parse error response", e);
        }
    }

}
