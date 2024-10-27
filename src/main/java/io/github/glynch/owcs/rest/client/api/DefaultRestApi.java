package io.github.glynch.owcs.rest.client.api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.client.exceptions.RestClientRequestException;
import io.github.glynch.owcs.rest.support.DefaultUriBuilder;
import io.github.glynch.owcs.rest.support.ResponseErrorHandler;
import io.github.glynch.owcs.rest.support.UriBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DefaultRestApi implements RestApi {

    private static final String LINK_HEADER = "Link";
    private static final Pattern LINK_PATTERN = Pattern.compile("<([^>]+)>");

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;
    private final io.github.glynch.owcs.rest.support.ResponseErrorHandler errorHandler;

    public DefaultRestApi(OkHttpClient client, ObjectMapper objectMapper, ResponseErrorHandler errorHandler)
            throws RestClientException {
        this.client = client;
        this.objectMapper = objectMapper;
        this.errorHandler = errorHandler;
    }

    @Override
    public Response execute(Request request) throws RestClientException {
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
        } catch (JsonProcessingException e) {
            throw new RestClientRequestException(request);
        } catch (IOException e) {
            throw new RestClientRequestException(request, e);
        }
        return data;
    }

    @Override
    public <T> T get(String url, Class<T> type) throws RestClientException {
        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .get()
                .build();

        return execute(request, type);
    }

    @Override
    public <T> T get(String uriTemplate, Function<UriBuilder, URI> uriFunction, Class<T> type)
            throws RestClientException {
        URI uri = uriFunction.apply(new DefaultUriBuilder(uriTemplate));
        return get(uri.toString(), type);
    }

    @Override
    public URI options(String url) throws RestClientException {

        Request request = new Request.Builder()
                .url(url)
                .method("OPTIONS", RequestBody.create("", null))
                .build();

        Response response = execute(request);

        String link = response.header(LINK_HEADER);
        Matcher matcher = LINK_PATTERN.matcher(link);

        try {
            return matcher.find() ? new URI(matcher.group(1)) : null;
        } catch (URISyntaxException e) {
            throw new RestClientRequestException(request, e);
        }

    }

    @Override
    public URI options(String uriTemplate, Function<UriBuilder, URI> uriFunction)
            throws RestClientException {
        URI uri = uriFunction.apply(new DefaultUriBuilder(uriTemplate));
        return options(uri.toString());
    }

    @Override
    public Map<String, String> head(String url) throws RestClientException {
        Request request = new Request.Builder()
                .url(url)
                .head()
                .build();
        Map<String, String> headers = new HashMap<>();

        Response response = execute(request);
        response.headers().forEach(
                header -> headers.put(header.getFirst(), header.getSecond()));

        return headers;
    }

    @Override
    public Map<String, String> head(String uriTemplate, Function<UriBuilder, URI> uriFunction)
            throws RestClientException {
        URI uri = uriFunction.apply(new DefaultUriBuilder(uriTemplate));
        return head(uri.toString());
    }

}
