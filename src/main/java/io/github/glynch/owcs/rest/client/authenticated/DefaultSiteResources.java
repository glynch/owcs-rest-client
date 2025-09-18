package io.github.glynch.owcs.rest.client.authenticated;

import com.fatwire.rest.beans.SiteBean;

import io.github.glynch.owcs.rest.client.RestClientException;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteResources;

public class DefaultSiteResources implements SiteResources {

    private final AuthenticatedRestClient restClient;
    private final String site;

    public DefaultSiteResources(AuthenticatedRestClient restClient, String site) {
        this.restClient = restClient;
        this.site = site;
    }

    @Override
    public SiteBean get() throws RestClientException {
        return restClient.get(restClient.getRestUrl() + SITE_URI_TEMPLATE, SiteBean.class, site);
    }

}
