package io.github.glynch.owcs.rest.client.v1;

import java.util.Map;

import io.github.glynch.owcs.rest.client.v1.V1RestClient.ResourceResources;

public class DefaultResourceResources implements ResourceResources {

    private final V1RestClient client;
    private final String version;

    public DefaultResourceResources(V1RestClient client, String version) {
        this.client = client;
        this.version = version;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, ?> metaDataCatalog() {
        return client.restApi().get(client.baseUrl() + RESOURCES_VERSION_METADATA_CATALOG_URI_TEMPLATE,
                builder -> builder.build(Map.of("version", version)), Map.class);
    }

}
