package io.github.glynch.owcs.rest.client.authenticated;

import org.springframework.util.Assert;

import com.fatwire.rest.beans.RoleBean;

import io.github.glynch.owcs.rest.client.RestClientException;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.RoleResources;

public class DefaultRoleResources implements RoleResources {

    private final AuthenticatedRestClient restClient;
    private final String role;

    DefaultRoleResources(AuthenticatedRestClient client, String role) {
        this.restClient = client;
        this.role = role;
    }

    @Override
    public RoleBean read() throws RestClientException {
        return restClient.get(ROLE_URI_TEMPLATE, RoleBean.class, role);
    }

    @Override
    public RoleBean create(RoleBean roleBean) throws RestClientException {
        Assert.notNull(roleBean, "roleBean cannot be null");
        return restClient.put(ROLE_URI_TEMPLATE, roleBean, RoleBean.class, role);
    }

    @Override
    public RoleBean update(RoleBean roleBean) throws RestClientException {
        Assert.notNull(roleBean, "roleBean cannot be null");
        return restClient.post(ROLE_URI_TEMPLATE, roleBean, RoleBean.class, role);
    }

    @Override
    public void delete() throws RestClientException {
        restClient.delete(ROLE_URI_TEMPLATE, role);
    }

}
