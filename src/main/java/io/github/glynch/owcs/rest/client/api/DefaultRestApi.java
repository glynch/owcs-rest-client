package io.github.glynch.owcs.rest.client.api;

import java.io.IOException;
import java.net.URI;
import java.util.function.Function;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.glynch.owcs.rest.client.api.exceptions.RestApiException;
import io.github.glynch.owcs.rest.client.api.exceptions.RuntimeIOException;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.client.exceptions.RestClientRequestException;
import io.github.glynch.owcs.rest.client.support.DefaultUriBuilder;
import io.github.glynch.owcs.rest.client.support.ResponseErrorHandler;
import io.github.glynch.owcs.rest.client.support.UriBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DefaultRestApi implements RestApi {

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;
    private final ResponseErrorHandler errorHandler;

    public DefaultRestApi(OkHttpClient client, ObjectMapper objectMapper, ResponseErrorHandler errorHandler)
            throws RestApiException {
        this.client = client;
        this.objectMapper = objectMapper;
        this.errorHandler = errorHandler;
    }

    @Override
    public Response execute(Request request) throws RuntimeIOException {
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RestClientRequestException(request, e);
        }
        return response;
    }

    @Override
    public <T> T execute(Request request, Class<T> type) throws RestClientException {
        Response response = execute(request);
        T data = null;
        try {
            if (response.isSuccessful()) {
                data = objectMapper.readValue(response.body().string(), type);
            } else {
                errorHandler.handleError(response);
            }
        } catch (IOException e) {
            throw new RestClientRequestException(request);
        }
        return data;
    }

    @Override
    public <T> T get(String url, Class<T> type) {
        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .get()
                .build();

        return execute(request, type);
    }

    @Override
    public <T> T get(String uriTemplate, Function<UriBuilder, URI> uriFunction, Class<T> type) {
        URI uri = uriFunction.apply(new DefaultUriBuilder(uriTemplate));
        return get(uri.toString(), type);
    }

}
