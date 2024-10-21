package io.github.glynch.owcs.rest.client.sso;

import javax.cache.Cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CachingTokenProvider implements TokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(CachingTokenProvider.class);
    private final TokenProvider delegate;
    private final Cache<String, String> cache;

    public CachingTokenProvider(Cache<String, String> cache, TokenProvider delegate) {
        this.delegate = delegate;
        this.cache = cache;
    }

    @Override
    public String getToken(String baseUrl, String username, String password) throws SSOException {
        String cacheKey = getCacheKey(baseUrl, username, password);
        String token = cache.get(cacheKey);
        if (token == null) {
            LOGGER.trace("Cache miss for {} using username {}", baseUrl, username);
            token = delegate.getToken(baseUrl, username, password);
            cache.put(cacheKey, token);
        } else {
            LOGGER.trace("Cache hit for {} using username ({)}", baseUrl, username, token);
        }

        return token;
    }

    private String getCacheKey(String baseUrl, String username, String password) {
        return baseUrl + username + password.hashCode();
    }

}
