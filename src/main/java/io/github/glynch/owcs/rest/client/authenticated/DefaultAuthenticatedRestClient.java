package io.github.glynch.owcs.rest.client.authenticated;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.fatwire.rest.beans.AssetTypesBean;
import com.fatwire.rest.beans.SitesBean;

import io.github.glynch.owcs.rest.client.RestClientException;

public class DefaultAuthenticatedRestClient implements AuthenticatedRestClient {

    private static final String REST_PATH = "/REST";
    private final RestTemplate restTemplate;
    private final String restUrl;
    private final String baseUrl;
    private final String username;
    private final String password;

    DefaultAuthenticatedRestClient(RestTemplate restTemplate, String baseUrl, String username,
            String password) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.restUrl = baseUrl + REST_PATH;
        this.username = username;
        this.password = password;
    }

    @Override
    public String getBaseUrl() {
        return this.baseUrl;
    }

    @Override
    public String getRestUrl() {
        return this.restUrl;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public <T> T get(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
        HttpEntity<?> requestEntity = HttpEntity.EMPTY;
        return execute(url, HttpMethod.GET, requestEntity, responseType, uriVariables);
    }

    @Override
    public <T> T get(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        HttpEntity<?> requestEntity = HttpEntity.EMPTY;
        return execute(url, HttpMethod.GET, requestEntity, responseType, uriVariables);
    }

    @Override
    public <T> T put(String url, T body, Class<T> responseType, Object... uriVariables) throws RestClientException {
        HttpEntity<T> requestEntity = new HttpEntity<>(body);
        return execute(url, HttpMethod.PUT, requestEntity, responseType, uriVariables);
    }

    @Override
    public <T> T put(String url, T body, Class<T> responseType, Map<String, ?> uriVariables)
            throws RestClientException {
        HttpEntity<T> requestEntity = new HttpEntity<>(body);
        return execute(url, HttpMethod.PUT, requestEntity, responseType, uriVariables);
    }

    @Override
    public <T> T post(String url, T body, Class<T> responseType, Object... uriVariables) throws RestClientException {
        HttpEntity<T> requestEntity = new HttpEntity<>(body);
        return execute(url, HttpMethod.POST, requestEntity, responseType,
                uriVariables);
    }

    @Override
    public <T> T post(String url, T body, Class<T> responseType, Map<String, ?> uriVariables)
            throws RestClientException {
        HttpEntity<T> requestEntity = new HttpEntity<>(body);
        return execute(url, HttpMethod.POST, requestEntity, responseType,
                uriVariables);
    }

    @Override
    public void delete(String url, Object... uriVariables) throws RestClientException {
        restTemplate.delete(url, uriVariables);
    }

    @Override
    public void delete(String url, Map<String, ?> uriVariables) throws RestClientException {
        restTemplate.delete(url, uriVariables);
    }

    @Override
    public AssetTypesBean types() throws RestClientException {
        return get(TYPES_URI_TEMPLATE, AssetTypesBean.class);
    }

    @Override
    public TypeResources type(String type) {
        Assert.hasText(type, "type cannot be null or empty");
        return new DefaultTypeResources(this, type);
    }

    @Override
    public SitesBean sites() throws RestClientException {
        return get(SITES_URI_TEMPLATE, SitesBean.class);
    }

    @Override
    public SiteResources site(String site) {
        Assert.hasText(site, "site cannot be null or empty");
        return new DefaultSiteResources(this, site);
    }

    private <T> T execute(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType,
            Object... uriVariables) throws RestClientException {
        return restTemplate.exchange(url, method, requestEntity, responseType,
                uriVariables).getBody();
    }

    private <T> T execute(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType,
            Map<String, ?> uriVariables) throws RestClientException {
        return restTemplate.exchange(url, method, requestEntity, responseType, uriVariables).getBody();
    }

}
