package io.github.glynch.owcs.rest.client.authenticated;

import java.util.Arrays;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.glynch.owcs.rest.client.LoggingClientHttpRequestInterceptor;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.Builder;
import io.github.glynch.owcs.rest.support.DefaultObjectMapper;
import io.github.glynch.owcs.sso.TokenProvider;
import io.github.glynch.owcs.sso.cache.CachingTokenProvider;

public class DefaultAuthenticatedRestClientBuilder implements AuthenticatedRestClient.Builder {

    private static final Logger LOGGER = LoggerFactory.getLogger("io.github.glynch.owcs.rest.client");

    private static final ObjectMapper objectMapper = new DefaultObjectMapper();
    private static final ClientHttpRequestInterceptor loggingInterceptor = new LoggingClientHttpRequestInterceptor();
    private static final OkHttp3ClientHttpRequestFactory requestFactory = new OkHttp3ClientHttpRequestFactory();
    static final ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler(objectMapper);
    private static final HttpMessageConverter<?> messageConverter = new MappingJackson2HttpMessageConverter(
            objectMapper);
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

        RestTemplate restTemplate = new RestTemplate();
        DefaultUriTemplateHandler uriTemplateHandler = new DefaultUriTemplateHandler();
        uriTemplateHandler.setBaseUrl(baseUrl + "/REST");
        restTemplate.setUriTemplateHandler(uriTemplateHandler);
        restTemplate.setErrorHandler(errorHandler);
        restTemplate.setMessageConverters(Collections.singletonList(messageConverter));
        ClientHttpRequestInterceptor headerInterceptor = new DefaultAuthenticatedClientHttpRequestInterceptor(
                tokenProvider, baseUrl, username, password);
        restTemplate.setInterceptors(Arrays.asList(headerInterceptor, loggingInterceptor));
        if (LOGGER.isTraceEnabled()) {
            restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(requestFactory));
        } else {
            restTemplate.setRequestFactory(requestFactory);
        }

        return new DefaultAuthenticatedRestClient(restTemplate, baseUrl, username, password);
    }

}
