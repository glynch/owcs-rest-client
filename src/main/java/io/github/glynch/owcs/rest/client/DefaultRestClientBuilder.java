package io.github.glynch.owcs.rest.client;

import java.time.Duration;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.glynch.owcs.rest.client.RestClient.Builder;
import io.github.glynch.owcs.rest.client.api.DefaultRestApi;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient;
import io.github.glynch.owcs.rest.client.authenticated.DefaultAuthenticatedRestClientBuilder;
import io.github.glynch.owcs.rest.client.errors.DefaultResponseErrorHandler;
import io.github.glynch.owcs.rest.client.errors.ResponseErrorHandler;
import io.github.glynch.owcs.rest.client.v1.DefaultV1RestClient;
import io.github.glynch.owcs.rest.client.v1.V1RestClient;
import io.github.glynch.owcs.rest.support.ObjectMapperFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class DefaultRestClientBuilder implements RestClient.Builder {

    private static final ObjectMapper objectMapper = ObjectMapperFactory.getInstance();
    private static final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    private final String baseUrl;
    private final OkHttpClient.Builder builder = new OkHttpClient.Builder();
    boolean allowRedirects = false;

    public DefaultRestClientBuilder(String baseUrl) {
        Objects.requireNonNull(baseUrl, "baseUrl cannot be null");
        this.baseUrl = baseUrl;
    }

    @Override
    public Builder allowRedirects() {
        allowRedirects = true;
        return this;
    }

    @Override
    public Builder info() {
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        builder.addInterceptor(loggingInterceptor);
        return this;
    }

    @Override
    public Builder trace() {
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        return this;
    }

    @Override
    public Builder connectTimeout(Duration duration) {
        Objects.requireNonNull(duration, "duration cannot be null");
        builder.connectTimeout(duration);
        return this;
    }

    @Override
    public Builder readTimeout(Duration duration) {
        builder.readTimeout(duration);
        return this;
    }

    @Override
    public AuthenticatedRestClient.Builder authenticated(
            String username, String password) {
        Objects.requireNonNull(username, "username cannot be null");
        Objects.requireNonNull(password, "password cannot be null");

        return new DefaultAuthenticatedRestClientBuilder(builder.build(), objectMapper, baseUrl, username, password);
    }

    @Override
    public V1RestClient build() {
        ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler(objectMapper);
        return new DefaultV1RestClient(new DefaultRestApi(builder.build(), objectMapper, errorHandler), baseUrl);
    }

}
