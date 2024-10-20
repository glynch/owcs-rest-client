package io.github.glynch.owcs.rest.client.api;

import java.io.IOException;
import java.net.URI;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.glynch.owcs.rest.client.api.exceptions.RestApiException;
import io.github.glynch.owcs.rest.client.support.DefaultUriBuilder;
import io.github.glynch.owcs.rest.client.support.UriBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DefaultRestApi implements RestApi {

    private static final String LINK_HEADER = "Link";
    private static final Pattern LINK_PATTERN = Pattern.compile("<([^>]+)>");
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public DefaultRestApi(OkHttpClient client, ObjectMapper objectMapper) throws RestApiException {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public void handleError(Response response) throws RestApiException {
        try {
            RestError error = objectMapper.readValue(response.body().string(), RestError.class);
            throw new RestApiException(
                    error);
        } catch (IOException e) {
            throw new RestApiException("Failed to parse error response", e);
        }
    }

    @Override
    public Response execute(Request request) throws RestApiException {
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response;
            } else {
                handleError(response);
            }
        } catch (Exception e) {
            throw new RestApiException(e.getMessage());
        }
        return response;
    }

    @Override
    public <T> T execute(Request request, Class<T> type) {
        T data = null;
        try {
            Response response = execute(request);
            data = objectMapper.readValue(response.body().string(), type);
        } catch (IOException e) {
            throw new RestApiException(e.getMessage());
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
    public <T> T get(String uriTemplate, Function<UriBuilder, URI> uriFunction, Class<T> clazz) {
        UriBuilder builder = new DefaultUriBuilder(uriTemplate);
        URI uri = uriFunction.apply(builder);
        return get(uri.toString(), clazz);
    }

    @Override
    public String options(String url) {
        Request request = new Request.Builder()
                .url(url)
                .method("OPTIONS", RequestBody.create("", null))
                .build();

        Response response = execute(request);

        String link = response.header(LINK_HEADER);
        Matcher matcher = LINK_PATTERN.matcher(link);

        return matcher.find() ? matcher.group(1) : null;
    }

}
