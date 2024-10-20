package io.github.glynch.owcs.rest.client.v1;

import io.github.glynch.owcs.rest.client.api.RestApi;

public class DefaultV1RestClient implements V1RestClient {

    private final RestApi restApi;
    private final String baseUrl;

    public DefaultV1RestClient(RestApi restApi, String baseUrl) {
        this.restApi = restApi;
        this.baseUrl = baseUrl;
    }

}
