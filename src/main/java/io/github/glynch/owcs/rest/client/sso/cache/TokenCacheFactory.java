package io.github.glynch.owcs.rest.client.sso.cache;

import java.util.Objects;
import java.util.UUID;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;

public class TokenCacheFactory {

    private static final CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();

    private TokenCacheFactory() {

    }

    public static Cache<String, String> createTokenCache(String name, Duration duration) {
        Objects.requireNonNull(name, "name must not be null");
        MutableConfiguration<String, String> configuration = new MutableConfiguration<String, String>();
        // Expire entries after 1 minute
        configuration
                .setExpiryPolicyFactory(AccessedExpiryPolicy
                        .factoryOf(duration));
        Cache<String, String> cache = cacheManager.createCache(name, configuration);
        return cache;
    }

    public static Cache<String, String> defaultTokenCache() {
        return createTokenCache(getCacheName(), Duration.TEN_MINUTES);
    }

    private static String getCacheName() {
        return "tokens-" + UUID.randomUUID();
    }

}
