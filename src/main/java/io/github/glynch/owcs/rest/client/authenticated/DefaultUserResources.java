package io.github.glynch.owcs.rest.client.authenticated;

import org.springframework.util.Assert;

import com.fatwire.rest.beans.UserBean;

import io.github.glynch.owcs.rest.client.RestClientException;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.UserResources;

public class DefaultUserResources implements UserResources {

    private final AuthenticatedRestClient restClient;
    private final String user;

    DefaultUserResources(AuthenticatedRestClient restClient, String user) {
        this.restClient = restClient;
        this.user = user;
    }

    @Override
    public UserBean read() throws RestClientException {
        return restClient.get(USER_URI_TEMPLATE, UserBean.class, user);
    }

    @Override
    public UserBean create(UserBean userBean) throws RestClientException {
        Assert.notNull(userBean, "userBean cannot be null");
        return restClient.put(USER_URI_TEMPLATE, userBean, UserBean.class, user);
    }

    @Override
    public UserBean update(UserBean userBean) throws RestClientException {
        return restClient.post(USER_URI_TEMPLATE, userBean, UserBean.class, user);
    }

    @Override
    public void delete() throws RestClientException {
        restClient.delete(USER_URI_TEMPLATE, user);
    }

}
