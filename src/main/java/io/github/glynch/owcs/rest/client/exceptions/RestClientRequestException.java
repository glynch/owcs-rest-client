package io.github.glynch.owcs.rest.client.exceptions;

import okhttp3.Request;

public class RestClientRequestException extends RestClientException {

    private static final long serialVersionUID = 1L;

    private final String method;
    private final String url;

    public RestClientRequestException(Request request, Throwable cause) {
        super(buildMessage(request), cause);
        this.method = request.method();
        this.url = request.url().toString();
    }

    public RestClientRequestException(Request request) {
        super(buildMessage(request));
        this.method = request.method();
        this.url = request.url().toString();
    }

    private static String buildMessage(Request request) {
        return String.format("Request failed: %s %s", request.method(), request.url());
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

}
