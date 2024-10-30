package io.github.glynch.owcs.rest.client.authenticated;

import static io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.TYPE_URI_TEMPLATE;

import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.NotImplementedException;

import com.fatwire.rest.beans.AssetTypeBean;
import com.fatwire.rest.beans.AssetTypesBean;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.TypeResources;
import io.github.glynch.owcs.rest.client.authenticated.search.SearchQuery;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.support.Subtypes;

public class DefaultTypeResources implements TypeResources {

    private final AuthenticatedRestClient client;
    private final String type;

    DefaultTypeResources(AuthenticatedRestClient client, String type) {
        this.client = client;
        this.type = type;
    }

    @Override
    public AssetTypeBean get() throws RestClientException {
        return client.restApi().get(client.baseUrl() + TYPE_URI_TEMPLATE,
                builder -> builder.build(Map.of("type", type)),
                AssetTypeBean.class);
    }

    @Override
    public void delete() throws RestClientException {
        client.restApi().delete(client.baseUrl() + TYPE_URI_TEMPLATE, builder -> builder.build(Map.of("type", type)));
    }

    @Override
    public AssetTypesBean subtypes() throws RestClientException {
        return client.restApi().get(client.baseUrl() + TYPE_SUBTYPES_URI_TEMPLATE,
                builder -> builder.build(Map.of("type", type)),
                AssetTypesBean.class);
    }

    @Override
    public AssetTypeBean subtype(String subtype) throws RestClientException {
        Objects.requireNonNull(subtype, "subtype is required");
        return client.restApi().get(client.baseUrl() + TYPE_SUBTYPE_URI_TEMPLATE,
                builder -> builder.build(Map.of("type", type, "subtype", subtype)),
                AssetTypeBean.class);
    }

    @Override
    public AssetTypeBean subtype(Subtypes subtype) throws RestClientException {
        Objects.requireNonNull(subtype, "subtype is required");
        return subtype(subtype.getName());
    }

    @Override
    public AssetTypesBean search(SearchQuery query) throws RestClientException {
        throw new NotImplementedException("Unimplemented method 'search'");
    }

}
