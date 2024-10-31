package io.github.glynch.owcs.rest.client.v1;

import java.util.Map;
import java.util.Objects;

import io.github.glynch.owcs.rest.client.api.RestApi;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.client.types.Sites;
import io.github.glynch.owcs.rest.client.types.Versions;
import io.github.glynch.owcs.rest.client.v1.search.V1SearchQuery;
import oracle.fatwire.rest.standard.beans.CollectionResourceMap;

public class DefaultV1RestClient implements V1RestClient {

    private final RestApi restApi;
    private final String baseUrl;

    public DefaultV1RestClient(RestApi restApi, String baseUrl) {
        this.restApi = restApi;
        this.baseUrl = baseUrl;
    }

    @Override
    public String baseUrl() {
        return baseUrl;
    }

    @Override
    public RestApi restApi() {
        return restApi;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> resources() throws RestClientException {
        return restApi.get(baseUrl + RESOURCES_URI_TEMPLATE, Map.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> resource(Versions version) throws RestClientException {
        Objects.requireNonNull(version, "version is required");
        return restApi.get(baseUrl + RESOURCES_VERSION_URI_TEMPLATE,
                builder -> builder.build(Map.of("version", version.getName())),
                Map.class);
    }

    @Override
    public ResourceResources resources(Versions version) throws RestClientException {
        Objects.requireNonNull(version, "version is required");
        return new DefaultResourceResources(this, version.getName());
    }

    @Override
    public SiteResources sites(Sites site) {
        Objects.requireNonNull(site, "site is required");
        return new DefaultSiteResources(this, site.getName());
    }

    @Override
    public CollectionResourceMap search(V1SearchQuery query) throws RestClientException {
        Objects.requireNonNull(query, "query is required");
        return restApi.get(baseUrl + SEARCH_URI_TEMPLATE,
                builder -> builder.queryParams(query.queryParams()).build(),
                CollectionResourceMap.class);
    }

    @Override
    public CollectionResourceMap search(String query) throws RestClientException {
        return search(V1SearchQuery.builder().q(query).build());
    }

    @Override
    public SearchResources search() throws RestClientException {
        return new DefaultSearchResources(this);
    }

}
