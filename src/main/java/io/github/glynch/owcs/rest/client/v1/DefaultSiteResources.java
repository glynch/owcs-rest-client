package io.github.glynch.owcs.rest.client.v1;

import java.util.Objects;

import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.client.v1.V1RestClient.SiteCollectionResources;
import io.github.glynch.owcs.rest.client.v1.V1RestClient.SiteRecommendationResources;
import io.github.glynch.owcs.rest.client.v1.V1RestClient.SiteResources;
import io.github.glynch.owcs.rest.client.v1.V1RestClient.SiteTypeResources;
import io.github.glynch.owcs.rest.support.Type;
import io.github.glynch.owcs.rest.support.Types;

public class DefaultSiteResources implements SiteResources {

    private final V1RestClient client;
    private final String site;

    public DefaultSiteResources(V1RestClient client, String site) {
        this.client = client;
        this.site = site;
    }

    @Override
    public SiteTypeResources types(Types type) throws RestClientException {
        Objects.requireNonNull(type, "type is required");
        return new DefaultSiteTypeResources(client, site, type.getName());
    }

    @Override
    public SiteCollectionResources contentQuery() {
        return new DefaultSiteCollectionResources(client, site, Type.CONTENTQUERY.getName());
    }

    @Override
    public SiteCollectionResources advCols() {
        return new DefaultSiteCollectionResources(client, site, Type.ADVCOLS.getName());
    }

    @Override
    public SiteRecommendationResources recommendation(String name) {
        return new DefaultSiteRecommendationResources(client, site, name);
    }

}
