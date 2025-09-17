package io.github.glynch.owcs.rest.client.authenticated;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.fatwire.rest.beans.AssetTypesBean;
import com.fatwire.rest.beans.SitesBean;

import io.github.glynch.owcs.rest.client.RestClientException;
import io.github.glynch.owcs.sso.TokenProvider;

public class DefaultAuthenticatedRestClient implements AuthenticatedRestClient {

    private final RestTemplate restTemplate;
    private final TokenProvider tokenProvider;
    private final String baseUrl;
    private final String username;
    private final String password;

    DefaultAuthenticatedRestClient(RestTemplate restTemplate, TokenProvider tokenProvider, String baseUrl,
            String username, String password) {
        this.restTemplate = restTemplate;
        this.tokenProvider = tokenProvider;
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
    }

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public TokenProvider getTokenProvider() {
        return tokenProvider;
    }

    @Override
    public <T> T get(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
        HttpHeaders headers = createHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<Void>(null, headers);
        return execute(url, HttpMethod.GET, requestEntity, responseType, uriVariables);
    }

    @Override
    public <T> T get(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        HttpHeaders headers = createHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<Void>(null, headers);
        return execute(url, HttpMethod.GET, requestEntity, responseType, uriVariables);
    }

    @Override
    public <T> T put(String url, T body, Class<T> responseType, Object... uriVariables) throws RestClientException {
        HttpHeaders headers = createHeaders();
        HttpEntity<T> requestEntity = new HttpEntity<>(body, headers);
        return execute(url, HttpMethod.PUT, requestEntity, responseType, uriVariables);
    }

    @Override
    public <T> T put(String url, T body, Class<T> responseType, Map<String, ?> uriVariables)
            throws RestClientException {
        HttpHeaders headers = createHeaders();
        HttpEntity<T> requestEntity = new HttpEntity<>(body, headers);
        return execute(url, HttpMethod.PUT, requestEntity, responseType, uriVariables);
    }

    // @Override
    // public <T> T post(String url, T body, Class<T> responseType, Object...
    // uriVariables) throws RestClientException {
    // HttpHeaders headers = createHeaders();
    // HttpEntity<T> requestEntity = new HttpEntity<>(body, headers);
    // return execute(url, HttpMethod.POST, requestEntity, responseType,
    // uriVariables);
    // }

    // @Override
    // public <T> T post(String url, T body, Class<T> responseType, Map<String, ?>
    // uriVariables) throws RestClientException {
    // HttpHeaders headers = createHeaders();
    // HttpEntity<T> requestEntity = new HttpEntity<>(body, headers);
    // return execute(url, HttpMethod.POST, requestEntity, responseType,
    // uriVariables);
    // }

    // @Override
    // public void delete(String url) throws RestClientException {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'delete'");
    // }

    @Override
    public AssetTypesBean types() throws RestClientException {
        return get(this.baseUrl + TYPES_URI_TEMPLATE, AssetTypesBean.class);
    }

    @Override
    public TypeResources type(String type) {
        Assert.hasText(type, "type cannot be null or empty");
        return new DefaultTypeResources(this, type);
    }

    @Override
    public SitesBean sites() throws RestClientException {
        return get(this.baseUrl + SITES_URI_TEMPLATE, SitesBean.class);
    }

    private <T> T execute(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType,
            Object... uriVariables) throws RestClientException {
        return restTemplate.exchange(url, method, requestEntity, responseType, uriVariables).getBody();
    }

    private <T> T execute(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType,
            Map<String, ?> uriVariables) throws RestClientException {
        return restTemplate.exchange(url, method, requestEntity, responseType, uriVariables).getBody();
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String token = tokenProvider.getToken(this.baseUrl, this.username, this.password);
        headers.add(TokenProvider.X_CSRF_TOKEN, token);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setPragma("auth-redirect=false");
        return headers;
    }

}
