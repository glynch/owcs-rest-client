package io.github.glynch.owcs.rest.client.v1.search;

import io.github.glynch.owcs.rest.client.v1.search.DefaultAssetQuery.DefaultAssetQueryBuilder;

public interface AssetQuery extends BaseQuery {

    static Builder builder() {
        return new DefaultAssetQueryBuilder();
    }

    interface Builder {

        Builder assetDepth(int assetDepth);

        Builder all();

        Builder links(Link... links);

        Builder profileName(String profileName);

        Builder segments(String... segments);

        AssetQuery build();

    }

}
