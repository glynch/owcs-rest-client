package io.github.glynch.owcs.rest.client.authenticated;

import java.util.Map;

import com.fatwire.rest.beans.UserBean;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.UserResources;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;

public class DefaultUserResources implements UserResources {

    private final AuthenticatedRestClient client;
    private final String user;

    DefaultUserResources(AuthenticatedRestClient client, String user) {
        this.client = client;
        this.user = user;
    }

    @Override
    public UserBean get() throws RestClientException {
        return client.restApi().get(client.baseUrl() + AuthenticatedRestClient.USER_URI_TEMPLATE,
                builder -> builder.build(Map.of("user", user)),
                UserBean.class);
    }

    @Override
    public void delete() throws RestClientException {
        client.restApi().delete(client.baseUrl() + AuthenticatedRestClient.USER_URI_TEMPLATE,
                builder -> builder.build(Map.of("user", user)));
    }

    @Override
    public Map<String, String> head() throws RestClientException {
        return client.restApi().head(client.baseUrl() + AuthenticatedRestClient.USER_URI_TEMPLATE,
                builder -> builder.build(Map.of("usernuserame", user)));
    }

}
