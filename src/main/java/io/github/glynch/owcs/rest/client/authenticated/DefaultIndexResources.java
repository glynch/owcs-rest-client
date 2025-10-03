package io.github.glynch.owcs.rest.client.authenticated;

import org.springframework.util.Assert;

import com.fatwire.rest.beans.IndexConfigBean;

import io.github.glynch.owcs.rest.client.RestClientException;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.IndexResources;

public class DefaultIndexResources implements IndexResources {

    private final AuthenticatedRestClient restClient;
    private final String index;

    DefaultIndexResources(AuthenticatedRestClient restClient, String index) {
        this.restClient = restClient;
        this.index = index;
    }

    @Override
    public IndexConfigBean read() throws RestClientException {
        return restClient.get(INDEX_URI_TEMPLATE, IndexConfigBean.class, index);
    }

    @Override
    public IndexConfigBean create(IndexConfigBean indexConfigBean) throws RestClientException {
        Assert.notNull(indexConfigBean, "indexConfigBean cannot be null");
        return restClient.put(INDEX_URI_TEMPLATE, indexConfigBean, IndexConfigBean.class, index);
    }

    @Override
    public IndexConfigBean update(IndexConfigBean indexConfigBean) throws RestClientException {
        Assert.notNull(indexConfigBean, "indexConfigBean cannot be null");
        return restClient.post(INDEX_URI_TEMPLATE, indexConfigBean, IndexConfigBean.class, index);
    }

    @Override
    public void delete() throws RestClientException {
        restClient.delete(INDEX_URI_TEMPLATE, index);
    }

}
