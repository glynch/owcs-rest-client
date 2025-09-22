package io.github.glynch.owcs.rest.client.authenticated;

import org.springframework.util.Assert;

import com.fatwire.rest.beans.ApplicationBean;

import io.github.glynch.owcs.rest.client.RestClientException;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.ApplicationResources;

public class DefaultApplicationResources implements ApplicationResources {

    private final AuthenticatedRestClient restClient;
    private final long applicationId;

    public DefaultApplicationResources(AuthenticatedRestClient restClient, long applicationId) {
        this.restClient = restClient;
        this.applicationId = applicationId;
    }

    @Override
    public ApplicationBean read() throws RestClientException {
        return restClient.get(APPLICATION_URI_TEMPLATE, ApplicationBean.class, applicationId);
    }

    @Override
    public ApplicationBean create(ApplicationBean applicationBean) throws RestClientException {
        Assert.notNull(applicationBean, "applicationBean cannot be null");
        return restClient.put(APPLICATION_URI_TEMPLATE, applicationBean, ApplicationBean.class, applicationId);
    }

    @Override
    public ApplicationBean update(ApplicationBean applicationBean) throws RestClientException {
        Assert.notNull(applicationBean, "applicationBean cannot be null");
        return restClient.post(APPLICATION_URI_TEMPLATE, applicationBean, ApplicationBean.class, applicationId);
    }

    @Override
    public void delete() throws RestClientException {
        restClient.delete(APPLICATION_URI_TEMPLATE, applicationId);
    }

}
