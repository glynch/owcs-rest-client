package io.github.glynch.owcs.rest.client.authenticated;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.glynch.owcs.rest.client.RestClientException;
import io.github.glynch.owcs.rest.support.DefaultObjectMapper;

public class DefaultResponseErrorHandler implements ResponseErrorHandler {

    private static final ResponseErrorHandler delegate = new org.springframework.web.client.DefaultResponseErrorHandler();
    private final ObjectMapper objectMapper;

    public DefaultResponseErrorHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public DefaultResponseErrorHandler() {
        this(new DefaultObjectMapper());
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return delegate.hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        try {
            delegate.handleError(response);
        } catch (HttpStatusCodeException e) {
            AuthenticatedRestError error = getError(e.getResponseBodyAsString());
            throw new AuthenticatedRestClientResponseException(error, e.getRawStatusCode(), e.getStatusText(),
                    e.getResponseBodyAsByteArray(), getCharset(response));
        } catch (IOException e) {
            throw new RestClientException(e.getMessage(), e);
        }
    }

    private AuthenticatedRestError getError(String responseBody) {
        try {
            return objectMapper.readValue(responseBody, AuthenticatedRestError.class);
        } catch (IOException e) {
            throw new RestClientException("Could not parse response body", e);
        }
    }

    private Charset getCharset(ClientHttpResponse response) {
        HttpHeaders headers = response.getHeaders();
        MediaType contentType = headers.getContentType();
        return contentType != null ? contentType.getCharset() : StandardCharsets.UTF_8;
    }

}
