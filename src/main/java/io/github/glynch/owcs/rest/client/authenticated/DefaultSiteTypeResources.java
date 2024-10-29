package io.github.glynch.owcs.rest.client.authenticated;

import java.util.Map;
import java.util.Objects;

import com.fatwire.rest.beans.AssetsBean;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteTypeAssetResources;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteTypeResources;
import io.github.glynch.owcs.rest.client.authenticated.search.AssetQuery;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;

public class DefaultSiteTypeResources implements SiteTypeResources {

    private final AuthenticatedRestClient client;
    private final String site;
    private final String type;

    DefaultSiteTypeResources(AuthenticatedRestClient client, String site, String type) {
        this.client = client;
        this.site = site;
        this.type = type;
    }

    @Override
    public SiteTypeAssetResources id(long id) throws RestClientException {
        return new DefaultSiteTypeAssetResources(client, site, type, id);
    }

    @Override
    public AssetsBean search(AssetQuery query) throws RestClientException {
        Objects.requireNonNull(query, "query cannot be null");
        return client.restApi().get(client.baseUrl() + SITE_TYPE_SEARCH_URI_TEMPLATE,
                builder -> builder.queryParams(query.queryParams()).build(Map.of("site", site, "type", type)),
                AssetsBean.class);
    }

}
