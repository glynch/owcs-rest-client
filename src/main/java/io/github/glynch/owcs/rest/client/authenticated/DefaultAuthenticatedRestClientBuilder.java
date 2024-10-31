package io.github.glynch.owcs.rest.client.authenticated;

import java.util.Objects;

import javax.cache.Cache;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.glynch.owcs.rest.client.api.DefaultAuthenticatedRestApi;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.Builder;
import io.github.glynch.owcs.rest.client.authenticated.errors.AuthenticatedResponseErrorHandler;
import io.github.glynch.owcs.rest.client.sso.CachingTokenProvider;
import io.github.glynch.owcs.rest.client.sso.DefaultTicketEncryptor;
import io.github.glynch.owcs.rest.client.sso.DefaultTicketProvider;
import io.github.glynch.owcs.rest.client.sso.DefaultTokenProvider;
import io.github.glynch.owcs.rest.client.sso.TokenProvider;
import io.github.glynch.owcs.rest.client.sso.cache.TokenCacheFactory;
import okhttp3.OkHttpClient;

public class DefaultAuthenticatedRestClientBuilder implements AuthenticatedRestClient.Builder {

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;
    private final String baseUrl;
    private final String username;
    private final String password;
    private TokenProvider tokenProvider;
    private Cache<String, String> cache;

    public DefaultAuthenticatedRestClientBuilder(OkHttpClient client, ObjectMapper objectMapper, String baseUrl,
            String username,
            String password) {
        this.client = client;
        this.objectMapper = objectMapper;
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
    }

    @Override
    public Builder tokenProvider(TokenProvider tokenProvider) {
        Objects.requireNonNull(tokenProvider, "tokenProvider cannot be null");
        this.tokenProvider = tokenProvider;
        return this;
    }

    @Override
    public Builder cachingTokenProvider(Cache<String, String> cache) {
        Objects.requireNonNull(cache, "cache cannot be null");
        this.cache = cache;
        return this;
    }

    @Override
    public Builder cachingTokenProvider() {
        this.cache = TokenCacheFactory.defaultTokenCache();
        return this;
    }

    @Override
    public AuthenticatedRestClient build() {
        if (tokenProvider == null) {
            tokenProvider = new DefaultTokenProvider(new DefaultTicketProvider(client),
                    new DefaultTicketEncryptor(client));
        }
        if (cache != null) {
            tokenProvider = new CachingTokenProvider(cache, tokenProvider);
        }
        AuthenticatedResponseErrorHandler errorHandler = new AuthenticatedResponseErrorHandler(objectMapper);
        DefaultAuthenticatedRestApi restApi = new DefaultAuthenticatedRestApi(client, objectMapper, tokenProvider,
                errorHandler,
                baseUrl, username,
                password);
        return new DefaultAuthenticatedRestClient(restApi, baseUrl);
    }

}
