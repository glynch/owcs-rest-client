package io.github.glynch.owcs.rest.client.api;

import java.net.URI;
import java.util.function.Function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.glynch.owcs.rest.client.api.exceptions.RestApiException;
import io.github.glynch.owcs.rest.client.authenticated.support.AuthenticatedResponseErrorHandler;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.client.sso.TokenProvider;
import io.github.glynch.owcs.rest.client.support.DefaultUriBuilder;
import io.github.glynch.owcs.rest.client.support.UriBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DefaultAuthenticatedRestApi extends DefaultRestApi implements AuthenticatedRestApi {

    private final ObjectMapper objectMapper;
    private final TokenProvider tokenProvider;
    private final String baseUrl;
    private final String username;
    private final String password;

    public DefaultAuthenticatedRestApi(OkHttpClient client, ObjectMapper objectMapper, TokenProvider tokenProvider,
            AuthenticatedResponseErrorHandler errorHandler,
            String baseUrl,
            String username, String password) {
        super(client, objectMapper, errorHandler);
        this.objectMapper = objectMapper;
        this.tokenProvider = tokenProvider;
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
    }

    @Override
    public Response execute(Request request) throws RestApiException {
        Request.Builder builder = new Request.Builder(request);
        builder.header(TokenProvider.X_CSRF_TOKEN,
                tokenProvider.getToken(baseUrl, username, password));
        return super.execute(builder.build());
    }

    @Override
    public <T> T execute(Request request, Class<T> type) throws RestClientException {
        Request.Builder builder = new Request.Builder(request);
        builder.header(TokenProvider.X_CSRF_TOKEN,
                tokenProvider.getToken(baseUrl, username, password));
        return super.execute(builder.build(), type);
    }

    @Override
    public <T> T put(String path, T body, Class<T> type) throws RestClientException {
        Request request = new Request.Builder()
                .url(path)
                .put(json(body))
                .build();

        return execute(request, type);
    }

    @Override
    public <T> T put(String uriTemplate, Function<UriBuilder, URI> uriFunction, T body, Class<T> type)
            throws RestClientException {
        UriBuilder builder = new DefaultUriBuilder(uriTemplate);
        URI uri = uriFunction.apply(builder);
        return put(uri.toString(), body, type);
    }

    @Override
    public <T> T post(String path, T body, Class<T> type) throws RestApiException {
        Request request = new Request.Builder()
                .url(path)
                .post(json(body))
                .build();

        return execute(request, type);
    }

    @Override
    public <T> T post(String uriTemplate, Function<UriBuilder, URI> uriFunction, T body, Class<T> type)
            throws RestApiException {
        UriBuilder builder = new DefaultUriBuilder(uriTemplate);
        URI uri = uriFunction.apply(builder);
        return post(uri.toString(), body, type);
    }

    @Override
    public void delete(String path) throws RestApiException {
        Request request = new Request.Builder()
                .url(path)
                .delete()
                .build();
        execute(request);
    }

    @Override
    public void delete(String uriTemplate, Function<UriBuilder, URI> uriFunction) throws RestApiException {
        UriBuilder builder = new DefaultUriBuilder(uriTemplate);
        URI uri = uriFunction.apply(builder);
        delete(uri.toString());
    }

    private RequestBody json(Object body) throws RestApiException {
        RequestBody requestBody = null;
        try {
            requestBody = RequestBody.create(objectMapper.writeValueAsString(body),
                    APPLICATION_JSON);
        } catch (JsonProcessingException e) {
            throw new RestApiException(e.getMessage());
        }
        return requestBody;
    }

}
