package io.github.glynch.owcs.rest.client.api;

import java.net.URI;
import java.util.Map;
import java.util.function.Function;

import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.support.UriBuilder;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

public interface RestApi {
    String ACCEPT_HEADER = "Accept";
    String CONTENT_TYPE_HEADER = "Content-Type";
    MediaType APPLICATION_JSON = MediaType.parse("application/json");

    Response execute(Request request) throws RestClientException;

    <T> T execute(Request request, Class<T> type) throws RestClientException;

    <T> T get(String url, Class<T> type) throws RestClientException;

    <T> T get(String uriTemplate, Function<UriBuilder, URI> uriFunction, Class<T> type) throws RestClientException;

    URI options(String url) throws RestClientException;

    URI options(String uriTemplate, Function<UriBuilder, URI> uriFunction)
            throws RestClientException;

    Map<String, String> head(String url) throws RestClientException;

    Map<String, String> head(String uriTemplate, Function<UriBuilder, URI> uriFunction)
            throws RestClientException;

}
