package io.github.glynch.owcs.rest.client.v1.support;

import static io.github.glynch.owcs.rest.client.v1.V1RestClient.SEARCH_URI_TEMPLATE;

import java.net.URI;
import java.util.Map;

import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.client.v1.V1RestClient;
import io.github.glynch.owcs.rest.client.v1.V1RestClient.SearchResources;

public class DefaultSearchResources implements SearchResources {

    private final V1RestClient client;

    public DefaultSearchResources(V1RestClient client) {
        this.client = client;
    }

    @SuppressWarnings("unchecked")
    public Map<String, ?> metaDataCatalog() {
        return client.restApi().get(client.baseUrl() + SEARCH_METADATA_CATALOG_URI_TEMPLATE,
                Map.class);
    }

    @Override
    public URI options() throws RestClientException {
        return client.restApi().options(client.baseUrl() + SEARCH_URI_TEMPLATE);
    }
}
