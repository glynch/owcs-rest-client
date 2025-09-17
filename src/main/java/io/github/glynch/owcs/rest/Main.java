package io.github.glynch.owcs.rest;

import com.fatwire.rest.beans.AssetTypeBean;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient;

public class Main {

    public static void main(String[] args) {

        AuthenticatedRestClient client = AuthenticatedRestClient
                .builder("http://localhost:7003/sites", "fwadmin",
                        "xceladmin")
                .cachingTokenProvider().build();
        AssetTypeBean assetTypeBean = client.type("AVIArticle").subtype("Article");

        System.out.println("Asset Type: " + assetTypeBean.getName());

        // // AssetTypesBean subtypes = client.type("FOO").subtypes();

        // AssetTypeBean updatedAssetTypeBean =
        // client.type("BAR").create(assetTypeBean);
        // if (updatedAssetTypeBean != null) {
        // System.out.println("Updated Asset Type: " + updatedAssetTypeBean.getName() +
        // ", Description: " + updatedAssetTypeBean.getDescription());
        // } else {
        // System.out.println("Failed to update asset type.");
        // }

        // com.fatwire.wem.sso.cas.CASProvider tokenProvider =
        // com.fatwire.wem.sso.cas.CasTokenProvider.create("http://localhost:7003/cas",
        // "fwadmin", "xceladmin");

    }

}
