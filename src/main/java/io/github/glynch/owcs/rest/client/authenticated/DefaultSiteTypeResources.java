package io.github.glynch.owcs.rest.client.authenticated;

import com.fatwire.rest.beans.AssetsBean;

import io.github.glynch.owcs.rest.client.RestClientException;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteTypeAssetResources;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteTypeResources;

public class DefaultSiteTypeResources implements SiteTypeResources {

    private final AuthenticatedRestClient restClient;
    private final String site;
    private final String type;

    public DefaultSiteTypeResources(AuthenticatedRestClient restClient, String site, String type) {
        this.restClient = restClient;
        this.site = site;
        this.type = type;
    }

    @Override
    public AssetsBean search() throws RestClientException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public SiteTypeAssetResources asset(long id) {
        return new DefaultSiteTypeAssetResources(restClient, site, type, id);
    }

}
