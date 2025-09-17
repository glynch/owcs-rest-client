package io.github.glynch.owcs.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class LoggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger("io.github.glynch.owcs.rest.client");

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        LOGGER.debug("Request URI: {}", request.getURI());
        LOGGER.debug("Request Method: {}", request.getMethod());
        LOGGER.debug("Request Headers: {}", request.getHeaders());
        LOGGER.trace("Request Body: {}", new String(body, StandardCharsets.UTF_8));
        ClientHttpResponse response = execution.execute(request, body);
        if (LOGGER.isTraceEnabled()) {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(response.getBody(), StandardCharsets.UTF_8))) {
                String responseBody = reader.lines()
                        .collect(Collectors.joining("\n"));
                LOGGER.trace("Response Status Code: {}", response.getStatusCode());
                LOGGER.trace("Response Status Text: {}", response.getStatusText());
                LOGGER.trace("Response Headers: {}", response.getHeaders());
                LOGGER.trace("Response Body: {}", responseBody);
            }
        }

        return response;
    }

}
