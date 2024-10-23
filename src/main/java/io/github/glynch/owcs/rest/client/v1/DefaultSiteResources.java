package io.github.glynch.owcs.rest.client.v1;

import java.util.Objects;

import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.client.v1.V1RestClient.SiteCollectionResources;
import io.github.glynch.owcs.rest.client.v1.V1RestClient.SiteRecommendationResources;
import io.github.glynch.owcs.rest.client.v1.V1RestClient.SiteResources;
import io.github.glynch.owcs.rest.client.v1.V1RestClient.SiteSearchResources;
import io.github.glynch.owcs.rest.client.v1.V1RestClient.SiteTypeResources;
import io.github.glynch.owcs.rest.client.v1.search.V1SearchQuery;
import io.github.glynch.owcs.rest.support.Type;
import io.github.glynch.owcs.rest.support.Types;
import oracle.fatwire.rest.standard.beans.CollectionResourceMap;

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

    @Override
    public CollectionResourceMap search(V1SearchQuery query) throws RestClientException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public CollectionResourceMap search(String query) throws RestClientException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public SiteSearchResources search() throws RestClientException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

}
