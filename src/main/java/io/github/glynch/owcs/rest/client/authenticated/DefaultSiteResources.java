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
    public SiteBean read() throws RestClientException {
        return restClient.get(SITE_URI_TEMPLATE, SiteBean.class, site);
    }

    @Override
    public SiteBean create(SiteBean siteBean) throws RestClientException {
        return restClient.put(SITE_URI_TEMPLATE, siteBean, SiteBean.class, site);
    }

    @Override
    public SiteBean update(SiteBean siteBean) throws RestClientException {
        return restClient.post(SITE_URI_TEMPLATE, siteBean, SiteBean.class, site);
    }

    @Override
    public void delete() throws RestClientException {
        restClient.delete(SITE_URI_TEMPLATE, site);
    }

    @Override
    public String head() throws RestClientException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'head'");
    }

}
