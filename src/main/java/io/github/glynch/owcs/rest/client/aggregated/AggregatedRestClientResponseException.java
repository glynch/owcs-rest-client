package io.github.glynch.owcs.rest.client.aggregated;

import java.nio.charset.Charset;

import io.github.glynch.owcs.rest.client.RestClientResponseException;

public class AggregatedRestClientResponseException extends RestClientResponseException {

    private final AggregatedRestError error;

    public AggregatedRestClientResponseException(AggregatedRestError error, int statusCode, String statusText, byte[] responseBody, Charset charSet) {
        super(error.getTitle(), statusCode, statusText, responseBody, charSet);
        this.error = error;
    }

    public AggregatedRestError getError() {
        return error;
    }



   

}
