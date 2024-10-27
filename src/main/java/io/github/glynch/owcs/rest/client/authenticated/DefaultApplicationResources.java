package io.github.glynch.owcs.rest.client.authenticated;

import java.util.Map;

import com.fatwire.rest.beans.ApplicationBean;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.ApplicationResources;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;

public class DefaultApplicationResources implements ApplicationResources {

    private final AuthenticatedRestClient client;
    private final long id;

    DefaultApplicationResources(AuthenticatedRestClient client, long id) {
        this.client = client;
        this.id = id;
    }

    @Override
    public void delete() throws RestClientException {
        client.restApi().delete(client.baseUrl() + AuthenticatedRestClient.APPLICATION_URI_TEMPLATE,
                builder -> builder.build(Map.of("id", id)));
    }

    @Override
    public ApplicationBean get() throws RestClientException {
        return client.restApi().get(client.baseUrl() + AuthenticatedRestClient.APPLICATION_URI_TEMPLATE,
                builder -> builder.build(Map.of("id", id)),
                ApplicationBean.class);
    }

}
