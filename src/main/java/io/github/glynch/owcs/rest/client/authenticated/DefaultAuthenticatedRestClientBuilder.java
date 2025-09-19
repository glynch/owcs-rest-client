package io.github.glynch.owcs.rest.client.authenticated;

import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.util.Assert;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.Builder;
import io.github.glynch.owcs.sso.TokenProvider;
import io.github.glynch.owcs.sso.cache.CachingTokenProvider;

public class DefaultAuthenticatedRestClientBuilder implements AuthenticatedRestClient.Builder {

    private static final OkHttp3ClientHttpRequestFactory requestFactory = new OkHttp3ClientHttpRequestFactory();
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
        requestFactory.setConnectTimeout(connectTimeOut);
        return this;
    }

    @Override
    public Builder readTimeOut(int readTimeOut) {
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

        DefaultAuthenticatedRestTemplate restTemplate = new DefaultAuthenticatedRestTemplate(requestFactory);

        restTemplate.getInterceptors()
                .add(new DefaultAuthenticatedClientHttpRequestInterceptor(tokenProvider, baseUrl, username, password));

        return new DefaultAuthenticatedRestClient(restTemplate, baseUrl, username, password);
    }

}
