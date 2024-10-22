package io.github.glynch.owcs.rest.client.v1;

import java.net.URI;
import java.util.Map;

import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.client.v1.V1RestClient.SiteCollectionResources;
import io.github.glynch.owcs.rest.client.v1.search.CollectionQuery;
import oracle.fatwire.rest.standard.beans.CollectionResourceMap;
import oracle.fatwire.rest.v1.maps.ResourceDescriptionMap;

public class DefaultSiteCollectionResources implements SiteCollectionResources {

    private final V1RestClient client;
    private final String site;
    private final String type;

    public DefaultSiteCollectionResources(V1RestClient client, String site, String type) {
        this.client = client;
        this.site = site;
        this.type = type;
    }

    @Override
    public CollectionResourceMap items(long id, CollectionQuery query) throws RestClientException {

        return client.restApi().get(client.baseUrl() + SITE_COLLECTION_ITEMS_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site, "type", type, "id", id)),
                CollectionResourceMap.class);
    }

    @Override
    public ResourceDescriptionMap metaDataCatalog(long id) throws RestClientException {
        return client.restApi().get(client.baseUrl() + SITE_COLLECTION_ITEMS_METADATA_CATALOG_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site, "type", type, "id", id)),
                ResourceDescriptionMap.class);
    }

    @Override
    public URI options(long id) throws RestClientException {
        return client.restApi().options(client.baseUrl() + SITE_COLLECTION_ITEMS_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site, "type", type, "id", id)));
    }

}
