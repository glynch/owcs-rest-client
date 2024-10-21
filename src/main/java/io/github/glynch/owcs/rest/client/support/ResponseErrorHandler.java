package io.github.glynch.owcs.rest.client.support;

import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import okhttp3.Response;

public interface ResponseErrorHandler {

    void handleError(Response response) throws RestClientException;

}
