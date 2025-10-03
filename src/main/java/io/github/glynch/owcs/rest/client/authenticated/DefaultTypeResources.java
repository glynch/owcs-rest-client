package io.github.glynch.owcs.rest.client.authenticated;

import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import com.fatwire.rest.beans.AssetTypeBean;
import com.fatwire.rest.beans.AssetTypesBean;
import com.fatwire.rest.beans.AssetsBean;

import io.github.glynch.owcs.rest.client.RestClientException;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.TypeResources;
import io.github.glynch.owcs.rest.client.authenticated.search.AssetSearchQuery;

public class DefaultTypeResources implements TypeResources {

    private final AuthenticatedRestClient restClient;
    private final String type;

    DefaultTypeResources(AuthenticatedRestClient restClient, String type) {
        this.restClient = restClient;
        this.type = type;
    }

    @Override
    public AssetTypeBean read() throws RestClientException {
        return restClient.get(TYPE_URI_TEMPLATE, AssetTypeBean.class, type);
    }

    @Override
    public AssetTypeBean create(AssetTypeBean assetTypeBean) throws RestClientException {
        Assert.notNull(assetTypeBean, "assetTypeBean cannot be null");
        return restClient.put(TYPE_URI_TEMPLATE, assetTypeBean, AssetTypeBean.class, type);
    }

    @Override
    public AssetTypesBean subtypes() throws RestClientException {
        return restClient.get(TYPE_SUBTYPES_URI_TEMPLATE, AssetTypesBean.class, type);
    }

    @Override
    public AssetTypeBean subtype(String subtype) {
        Assert.hasText(subtype, "subtype cannot be null or empty");
        return restClient.get(TYPE_SUBTYPE_URI_TEMPLATE, AssetTypeBean.class, type,
                subtype);
    }

    @Override
    public void delete() throws RestClientException {
        restClient.delete(TYPE_URI_TEMPLATE, type);
    }

    public AssetsBean search(AssetSearchQuery query) throws RestClientException {
        AssetsBean assetsBean = null;
        if (query != null) {
            UriComponentsBuilder builder = UriComponentsBuilder.fromPath(TYPE_SEARCH_URI_TEMPLATE);
            builder.queryParams(query.queryParams());
            assetsBean = restClient.get(builder.build(false).toUriString(), AssetsBean.class, type);
        } else {
            assetsBean = restClient.get(TYPE_SEARCH_URI_TEMPLATE, AssetsBean.class, type);
        }
        return assetsBean;
    }

    @Override
    public AssetsBean search() throws RestClientException {
        return search(null);
    }
}
