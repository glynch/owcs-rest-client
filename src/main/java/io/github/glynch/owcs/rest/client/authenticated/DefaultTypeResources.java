package io.github.glynch.owcs.rest.client.authenticated;

import org.springframework.util.Assert;

import com.fatwire.rest.beans.AssetTypeBean;
import com.fatwire.rest.beans.AssetTypesBean;

import io.github.glynch.owcs.rest.client.RestClientException;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.TypeResources;

public class DefaultTypeResources implements TypeResources {

    private final AuthenticatedRestClient restClient;
    private final String type;

    public DefaultTypeResources(AuthenticatedRestClient restClient, String type) {
        this.restClient = restClient;
        this.type = type;
    }

    @Override
    public AssetTypeBean read() throws RestClientException {
        return restClient.get(restClient.getRestUrl() + TYPE_URI_TEMPLATE, AssetTypeBean.class, type);
    }

    @Override
    public AssetTypeBean create(AssetTypeBean assetTypeBean) throws RestClientException {
        Assert.notNull(assetTypeBean, "assetTypeBean cannot be null");
        return restClient.put(restClient.getRestUrl() + TYPE_URI_TEMPLATE, assetTypeBean, AssetTypeBean.class, type);
    }

    @Override
    public AssetTypesBean subtypes() throws RestClientException {
        return restClient.get(restClient.getRestUrl() + TYPE_SUBTYPES_URI_TEMPLATE, AssetTypesBean.class, type);
    }

    @Override
    public AssetTypeBean subtype(String subtype) {
        Assert.hasText(subtype, "subtype cannot be null or empty");
        return restClient.get(restClient.getRestUrl() + TYPE_SUBTYPES_SUBTYPE_URI_TEMPLATE, AssetTypeBean.class, type,
                subtype);
    }
}
