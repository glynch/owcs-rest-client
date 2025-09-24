package io.github.glynch.owcs.rest.client.authenticated;

import org.springframework.util.Assert;

import com.fatwire.assetapi.data.AssetId;
import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssociationBean;
import com.fatwire.rest.beans.AssociationsBean;

import io.github.glynch.owcs.rest.bean.utils.AssetIds;
import io.github.glynch.owcs.rest.client.RestClientException;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SiteTypeAssetResources;

public class DefaultSiteTypeAssetResources implements SiteTypeAssetResources {

    private final AuthenticatedRestClient restClient;
    private final String site;
    private final String type;
    private final long id;

    public DefaultSiteTypeAssetResources(AuthenticatedRestClient restClient, String site, String type, long id) {
        this.restClient = restClient;
        this.site = site;
        this.type = type;
        this.id = id;
    }

    @Override
    public AssetBean read() throws RestClientException {
        return restClient.get(SITE_TYPE_ASSET_URI_TEMPLATE, AssetBean.class, site, type, id);
    }

    @Override
    public AssetBean create(AssetBean assetBean) throws RestClientException {
        Assert.notNull(assetBean, "assetBean cannot be null");
        if (id != 0) {
            throw new IllegalArgumentException("id must be 0 for asset create");
        }
        return restClient.put(SITE_TYPE_ASSET_URI_TEMPLATE, assetBean, AssetBean.class, site, type, 0L);
    }

    @Override
    public AssetBean update(AssetBean assetBean) throws RestClientException {
        Assert.notNull(assetBean, "assetBean cannot be null");
        AssetId assetId = AssetIds.of(assetBean.getId());
        if (id != assetId.getId()) {
            throw new IllegalArgumentException(String.format("id does not much assetBean %d %d", id, assetId.getId()));
        }
        return restClient.post(SITE_TYPE_ASSET_URI_TEMPLATE, assetBean, AssetBean.class, site, type, assetId.getId());
    }

    @Override
    public AssociationsBean associations() throws RestClientException {
        return restClient.get(SITE_TYPE_ASSET_ASSOCIATIONS_URI_TEMPLATE, AssociationsBean.class, site, type, id);
    }

    @Override
    public AssociationBean association(String assocName) throws RestClientException {
        Assert.hasText(assocName, "assocName cannot be empty or null");
        return restClient.get(SITE_TYPE_ASSET_ASSOCIATION_URI_TEMPLATE, AssociationBean.class, site, type, id,
                assocName);

    }

    @Override
    public void delete() throws RestClientException {
        restClient.delete(SITE_TYPE_ASSET_ASSOCIATIONS_URI_TEMPLATE, site, type, id);
    }

}
