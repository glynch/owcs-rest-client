package io.github.glynch.owcs.rest.client.authenticated;

import static io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteTypeResources.SITE_TYPE_ASSET_URI_TEMPLATE;

import java.util.Map;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssociationBean;
import com.fatwire.rest.beans.AssociationsBean;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteTypeAssetResources;
import io.github.glynch.owcs.rest.client.exceptions.RestClientException;

public class DefaultSiteTypeAssetResources implements SiteTypeAssetResources {

    private final AuthenticatedRestClient client;
    private final String site;
    private final String type;
    private final long id;

    public DefaultSiteTypeAssetResources(AuthenticatedRestClient client, String site, String type, long id) {
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
    public AssociationBean association(String association) throws RestClientException {
        return client.restApi().get(client.baseUrl() + SITE_TYPE_ASSET_ASSOCIATION_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site, "type", type, "id", id, "association", association)),
                AssociationBean.class);
    }

    @Override
    public AssetBean put(AssetBean assetBean) throws RestClientException {
        return client.restApi().put(client.baseUrl() + SITE_TYPE_ASSET_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site, "type", type, "id", 0L)),
                assetBean,
                AssetBean.class);
    }

    @Override
    public AssetBean post(AssetBean assetBean) throws RestClientException {
        return client.restApi().post(client.baseUrl() + SITE_TYPE_ASSET_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site, "type", type, "id", assetBean.getId())),
                assetBean,
                AssetBean.class);
    }

    @Override
    public void delete() throws RestClientException {
        client.restApi().delete(client.baseUrl() + SITE_TYPE_ASSET_URI_TEMPLATE,
                builder -> builder.build(Map.of("site", site, "type", type, "id", id)));
    }

}
