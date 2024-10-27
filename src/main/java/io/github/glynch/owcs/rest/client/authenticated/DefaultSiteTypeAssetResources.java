package io.github.glynch.owcs.rest.client.authenticated;

import static io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteTypeResources.SITE_TYPE_ASSET_URI_TEMPLATE;
import static io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteTypeResources.SITE_TYPE_URI_TEMPLATE;

import java.util.Map;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssociationBean;
import com.fatwire.rest.beans.AssociationsBean;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteTypeAssetResources;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;
import io.github.glynch.owcs.rest.support.Associations;

public class DefaultSiteTypeAssetResources implements SiteTypeAssetResources {

    private final AuthenticatedRestClient client;
    private final String site;
    private final String type;
    private final long id;

    DefaultSiteTypeAssetResources(AuthenticatedRestClient client, String site, String type, long id) {
        this.client = client;
        this.site = site;
        this.type = type;
        this.id = id;
    }

    @Override
    public AssociationsBean associations() throws RestClientException {
        return client.restApi().get(client.baseUrl() + SITE_TYPE_ASSET_ASSOCIATIONS_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site, "type", type, "id", id)),
                AssociationsBean.class);
    }

    @Override
    public AssociationBean association(Associations association) throws RestClientException {
        return client.restApi().get(client.baseUrl() + SITE_TYPE_ASSET_ASSOCIATION_URI_TEMPLATE,
                builder -> builder
                        .build(Map.of("site", site, "type", type, "id", id, "association", association.getName())),
                AssociationBean.class);
    }

    @Override
    public void delete() throws RestClientException {
        client.restApi().delete(client.baseUrl() + SITE_TYPE_ASSET_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site, "type", type, "id", id)));
    }

    @Override
    public AssetBean get() throws RestClientException {
        return client.restApi().get(client.baseUrl() + SITE_TYPE_ASSET_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site, "type", type, "id", id)),
                AssetBean.class);
    }

    @Override
    public Map<String, String> head() throws RestClientException {
        return client.restApi().head(client.baseUrl() + SITE_TYPE_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site, "type", type, "id", id)));
    }

}
