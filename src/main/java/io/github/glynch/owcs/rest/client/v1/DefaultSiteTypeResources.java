package io.github.glynch.owcs.rest.client.v1;

import java.util.Map;

import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.client.v1.V1RestClient.SiteTypeResources;
import io.github.glynch.owcs.rest.client.v1.search.AssetQuery;
import oracle.fatwire.rest.standard.beans.CollectionResourceMap;
import oracle.fatwire.rest.v1.maps.ResourceDescriptionMap;

public class DefaultSiteTypeResources implements SiteTypeResources {

    private final V1RestClient client;
    private final String site;
    private String type;

    public DefaultSiteTypeResources(V1RestClient client, String site, String type) {
        this.client = client;
        this.site = site;
        this.type = type;
    }

    @Override
    public CollectionResourceMap id(long id, AssetQuery query) throws RestClientException {
        return client.restApi().get(client.baseUrl() + SITE_TYPE_ASSET_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site, "type", type, "id", id)),
                CollectionResourceMap.class);
    }

    @Override
    public CollectionResourceMap id(long id) throws RestClientException {
        return id(id, AssetQuery.builder().build());
    }

    @Override
    public ResourceDescriptionMap metaDataCatalog(long id) throws RestClientException {
        return client.restApi().get(client.baseUrl() + SITE_TYPE_ASSET_METADATA_CATALOG_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site, "type", type, "id", id)),
                ResourceDescriptionMap.class);
    }

}
