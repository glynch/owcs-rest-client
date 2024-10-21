package io.github.glynch.owcs.rest.client.api;

import java.net.URI;
import java.util.function.Function;

import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.client.support.UriBuilder;

public interface AuthenticatedRestApi extends RestApi {

    <T> T put(String path, T body, Class<T> type) throws RestClientException;

    <T> T put(String uriTemplate, Function<UriBuilder, URI> uriFunction, T body, Class<T> type)
            throws RestClientException;

    <T> T post(String path, T body, Class<T> type) throws RestClientException;

    <T> T post(String uriTemplate, Function<UriBuilder, URI> uriFunction, T body, Class<T> type)
            throws RestClientException;

    void delete(String path) throws RestClientException;

    void delete(String uriTemplate, Function<UriBuilder, URI> uriFunction) throws RestClientException;

}
