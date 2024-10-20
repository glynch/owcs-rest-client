package io.github.glynch.owcs.rest.client.authenticated;

import static io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.ASSETTYPE_URI_TEMPLATE;

import java.util.Map;
import java.util.Objects;

import com.fatwire.rest.beans.AssetTypeBean;
import com.fatwire.rest.beans.AssetTypesBean;

import io.github.glynch.owcs.rest.client.api.AuthenticatedRestApi;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.AssetTypeResources;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;

public class DefaultAssetTypeResources implements AssetTypeResources {

    private final AuthenticatedRestApi restApi;
    private final String baseUrl;
    private final String type;

    DefaultAssetTypeResources(AuthenticatedRestApi restApi, String baseUrl, String type) {
        this.restApi = restApi;
        this.baseUrl = baseUrl;
        this.type = type;
    }

    @Override
    public AssetTypeBean get() throws RestClientException {
        return restApi.get(baseUrl + ASSETTYPE_URI_TEMPLATE, builder -> builder.build(Map.of("type", type)),
                AssetTypeBean.class);
    }

    @Override
    public AssetTypeBean put(AssetTypeBean assetType) throws RestClientException {
        return restApi.put(baseUrl + ASSETTYPE_URI_TEMPLATE, builder -> builder.build(Map.of("type", type)), assetType,
                AssetTypeBean.class);
    }

    @Override
    public void delete() throws RestClientException {
        restApi.delete(baseUrl + ASSETTYPE_URI_TEMPLATE, builder -> builder.build(Map.of("type", type)));
    }

    @Override
    public AssetTypesBean subtypes() throws RestClientException {
        return restApi.get(baseUrl + ALL_SUBTYPES_URI_TEMPLATE, builder -> builder.build(Map.of("type", type)),
                AssetTypesBean.class);
    }

    @Override
    public AssetTypeBean subtypes(String subtype) throws RestClientException {
        Objects.requireNonNull(subtype, "subtype is required");
        return restApi.get(baseUrl + SUBTYPE_URI_TEMPLATE,
                builder -> builder.build(Map.of("type", type, "subtype", subtype)),
                AssetTypeBean.class);
    }

}
