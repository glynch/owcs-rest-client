package io.github.glynch.owcs.rest.client.api.exceptions;

import okhttp3.Response;

public interface ErrorHandler {

    void handleError(Response response);

}
