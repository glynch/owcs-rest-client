package io.github.glynch.owcs.rest.client.authenticated;

import java.util.Map;

import com.fatwire.rest.beans.AssetTypeBean;
import com.fatwire.rest.beans.EnabledTypesBean;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteResources;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteTypeResources;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.client.support.Types;

public class DefaultSiteResources implements SiteResources {

    private final AuthenticatedRestClient client;
    private final String site;

    DefaultSiteResources(AuthenticatedRestClient client, String site) {
        this.client = client;
        this.site = site;
    }

    @Override
    public EnabledTypesBean types() throws RestClientException {
        return client.restApi().get(client.baseUrl() + SITE_TYPES_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site)),
                EnabledTypesBean.class);
    }

    @Override
    public AssetTypeBean type(Types type) throws RestClientException {
        return client.type(type);
    }

    @Override
    public SiteTypeResources types(Types type) throws RestClientException {
        return new DefaultSiteTypeResources(client, site, type.getName());
    }

}
