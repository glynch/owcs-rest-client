package io.github.glynch.owcs.rest.client.v1;

import java.net.URI;
import java.util.Map;

import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.client.v1.V1RestClient.SiteRecommendationResources;
import io.github.glynch.owcs.rest.client.v1.search.RecommendationQuery;
import oracle.fatwire.rest.standard.beans.CollectionResourceMap;
import oracle.fatwire.rest.v1.maps.ResourceDescriptionMap;

public class DefaultSiteRecommendationResources implements SiteRecommendationResources {

    private final V1RestClient client;
    private final String site;
    private final String name;

    public DefaultSiteRecommendationResources(V1RestClient client, String site, String name) {
        this.client = client;
        this.site = site;
        this.name = name;
    }

    @Override
    public CollectionResourceMap items(RecommendationQuery query) throws RestClientException {
        return client.restApi().get(client.baseUrl() + SITE_RECOMMENDATION_ITEMS_URI_TEMPLATE,
                builder -> builder.queryParams(query.queryParams()).build(Map.of("site", site, "name", name)),
                CollectionResourceMap.class);
    }

    @Override
    public CollectionResourceMap items() throws RestClientException {
        return items(RecommendationQuery.builder().build());
    }

    @Override
    public URI options() {
        return client.restApi().options(client.baseUrl() + SITE_RECOMMENDATION_ITEMS_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site, "name", name)));
    }

    @Override
    public ResourceDescriptionMap metaDataCatalog() {
        return client.restApi().get(client.baseUrl() + SITE_RECOMMENDATION_ITEMS_METADATA_CATALOG_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site, "name", name)), ResourceDescriptionMap.class);
    }

}
