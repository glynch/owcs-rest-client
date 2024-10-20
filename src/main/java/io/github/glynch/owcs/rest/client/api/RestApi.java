package io.github.glynch.owcs.rest.client.api;

import java.net.URI;
import java.util.function.Function;

import io.github.glynch.owcs.rest.client.api.exceptions.RestApiException;
import io.github.glynch.owcs.rest.client.support.UriBuilder;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

public interface RestApi {

    String ACCEPT_HEADSER = "Accept";
    String CONTENT_TYPE_HEADER = "Content-Type";
    MediaType APPLICATION_JSON = MediaType.parse("application/json");

    Response execute(Request request) throws RestApiException;

    <T> T execute(Request request, Class<T> type) throws RestApiException;

    <T> T get(String path, Class<T> type) throws RestApiException;

    <T> T get(String uriTemplate, Function<UriBuilder, URI> uriFunction, Class<T> clazz) throws RestApiException;

    String options(String path) throws RestApiException;

}
