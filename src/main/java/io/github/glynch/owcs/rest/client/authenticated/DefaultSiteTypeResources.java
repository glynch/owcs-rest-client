package io.github.glynch.owcs.rest.client.authenticated;

import org.springframework.web.util.UriComponentsBuilder;

import com.fatwire.rest.beans.AssetsBean;

import io.github.glynch.owcs.rest.client.RestClientException;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteTypeAssetResources;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteTypeResources;
import io.github.glynch.owcs.rest.client.authenticated.search.AssetSearchQuery;

public class DefaultSiteTypeResources implements SiteTypeResources {

    private final AuthenticatedRestClient restClient;
    private final String site;
    private final String type;

    DefaultSiteTypeResources(AuthenticatedRestClient restClient, String site, String type) {
        this.restClient = restClient;
        this.site = site;
        this.type = type;
    }

    @Override
    public AssetsBean search(AssetSearchQuery query) throws RestClientException {
        AssetsBean assetsBean = null;
        if (query != null) {
            UriComponentsBuilder builder = UriComponentsBuilder.fromPath(SITE_TYPE_SEARCH_URI_TEMPLATE);
            builder.queryParams(query.queryParams());
            assetsBean = restClient.get(builder.build(false).toUriString(), AssetsBean.class, site, type);
        } else {
            assetsBean = restClient.get(SITE_TYPE_SEARCH_URI_TEMPLATE, AssetsBean.class, site, type);
        }
        return assetsBean;
    }

    @Override
    public AssetsBean search() throws RestClientException {
        return search(null);
    }

    @Override
    public SiteTypeAssetResources asset(long id) {
        return new DefaultSiteTypeAssetResources(restClient, site, type, id);
    }

}
