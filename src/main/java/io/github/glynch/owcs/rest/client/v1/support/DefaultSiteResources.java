package io.github.glynch.owcs.rest.client.v1.support;

import java.util.Objects;

import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.client.v1.V1RestClient;
import io.github.glynch.owcs.rest.client.v1.V1RestClient.SiteResources;
import io.github.glynch.owcs.rest.client.v1.V1RestClient.SiteTypeResources;
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

}
