package io.github.glynch.owcs.rest.client.authenticated;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteTypeAssetResources;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteTypeResources;
import io.github.glynch.owcs.rest.client.authenticated.support.DefaultSiteTypeAssetResources;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;

public class DefaultSiteTypeResources implements SiteTypeResources {

    private final AuthenticatedRestClient client;
    private final String site;
    private final String type;

    public DefaultSiteTypeResources(AuthenticatedRestClient client, String site, String type) {
        this.client = client;
        this.site = site;
        this.type = type;
    }

    @Override
    public SiteTypeAssetResources assets() throws RestClientException {
        return new DefaultSiteTypeAssetResources(client, site, type);
    }

}
