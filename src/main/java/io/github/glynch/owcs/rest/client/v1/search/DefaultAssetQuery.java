package io.github.glynch.owcs.rest.client.v1.search;

public class DefaultAssetQuery extends AbstractBaseQuery implements AssetQuery {

    public DefaultAssetQuery(String assetDepth, Link[] links, String profileName, String[] segments) {
        super(assetDepth, links, profileName, segments);
    }

    public static class DefaultAssetQueryBuilder implements AssetQuery.Builder {

        private String assetDepth;
        private Link[] links;
        private String profileName;
        private String[] segments;

        public DefaultAssetQueryBuilder() {
        }

        @Override
        public AssetQuery.Builder assetDepth(String assetDepth) {
            this.assetDepth = assetDepth;
            return this;
        }

        @Override
        public AssetQuery.Builder links(Link... links) {
            this.links = links;
            return this;
        }

        @Override
        public AssetQuery.Builder profileName(String profileName) {
            this.profileName = profileName;
            return this;
        }

        @Override
        public AssetQuery.Builder segments(String... segments) {
            this.segments = segments;
            return this;
        }

        @Override
        public AssetQuery build() {
            return new DefaultAssetQuery(assetDepth, links, profileName, segments);
        }
    }

}
