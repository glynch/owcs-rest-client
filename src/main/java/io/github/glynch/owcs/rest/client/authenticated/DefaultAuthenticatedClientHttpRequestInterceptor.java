package io.github.glynch.owcs.rest.client.authenticated;

import java.io.IOException;
import java.util.Collections;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import io.github.glynch.owcs.sso.TokenProvider;

public class DefaultAuthenticatedClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private final TokenProvider tokenProvider;
    private final String baseUrl;
    private final String username;
    private final String password;

    public DefaultAuthenticatedClientHttpRequestInterceptor(TokenProvider tokenProvider, String baseUrl,
            String username, String password) {
        this.tokenProvider = tokenProvider;
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        HttpHeaders headers = request.getHeaders();
        String token = tokenProvider.getToken(this.baseUrl, this.username, this.password);
        headers.add(TokenProvider.X_CSRF_TOKEN, token);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setPragma("auth-redirect=false");
        return execution.execute(request, body);
    }

}
