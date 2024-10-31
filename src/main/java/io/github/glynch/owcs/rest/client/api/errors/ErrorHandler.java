package io.github.glynch.owcs.rest.client.api.errors;

import okhttp3.Response;

public interface ErrorHandler {

    void handleError(Response response);

}
