package io.github.glynch.owcs.rest.client;

import java.nio.charset.Charset;

public class RestClientResponseException extends RestClientException {

    private final int statusCode;
    private final String statusText;
    private final byte[] responseBody;
    private final Charset charSet;

    public RestClientResponseException(String msg, int statusCode, String statusText, byte[] responseBody, Charset charSet) {
        super(msg);
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.responseBody = responseBody;
        this.charSet = charSet;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public byte[] getResponseBody() {
        return responseBody;
    }

    public Charset getCharSet() {
        return charSet;
    }

    public String getResponseBodyAsString() {
       return new String(responseBody, charSet);
    }



}
