package io.github.glynch.owcs.rest.client.authenticated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.OkHttpClientHttpRequestFactory;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.Builder;
import io.github.glynch.owcs.sso.TokenProvider;
import io.github.glynch.owcs.sso.cache.CachingTokenProvider;

public class DefaultAuthenticatedRestClientBuilder implements AuthenticatedRestClient.Builder {

    private static final Logger LOGGER = LoggerFactory.getLogger("io.github.glynch.owcs.rest.client");
    private static final RestTemplate restTemplate = new DefaultAuthenticatedRestTemplate();

    private final String baseUrl;
    private final String username;
    private final String password;
    private TokenProvider tokenProvider;

    DefaultAuthenticatedRestClientBuilder(String baseUrl,
            String username,
            String password) {
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
    }

    @Override
    public Builder connectTimeOut(int connectTimeOut) {
        OkHttpClientHttpRequestFactory requestFactory = (OkHttpClientHttpRequestFactory) restTemplate
                .getRequestFactory();
        requestFactory.setConnectTimeout(connectTimeOut);
        return this;
    }

    @Override
    public Builder readTimeOut(int readTimeOut) {
        OkHttpClientHttpRequestFactory requestFactory = (OkHttpClientHttpRequestFactory) restTemplate
                .getRequestFactory();
        requestFactory.setReadTimeout(readTimeOut);
        return this;
    }

    @Override
    public Builder tokenProvider(TokenProvider tokenProvider) {
        Assert.notNull(tokenProvider, "tokenProvider cannot be null");
        this.tokenProvider = tokenProvider;
        return this;
    }

    @Override
    public Builder cachingTokenProvider() {
        this.tokenProvider = CachingTokenProvider.create();
        return this;
    }

    @Override
    public AuthenticatedRestClient build() {
        if (tokenProvider == null) {
            tokenProvider = TokenProvider.create();
        }

        return new DefaultAuthenticatedRestClient(restTemplate, tokenProvider, baseUrl, username, password);
    }

}
