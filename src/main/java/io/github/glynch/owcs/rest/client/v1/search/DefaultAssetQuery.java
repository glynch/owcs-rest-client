package io.github.glynch.owcs.rest.client.v1.search;

import java.util.Objects;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

public class DefaultAssetQuery extends AbstractBaseQuery implements AssetQuery {

    public DefaultAssetQuery(MultiValuedMap<String, String> queryParams) {
        super(queryParams);
    }

    public static class DefaultAssetQueryBuilder implements AssetQuery.Builder {

        private final MultiValuedMap<String, String> queryParams = new ArrayListValuedHashMap<>();

        public DefaultAssetQueryBuilder() {
        }

        @Override
        public AssetQuery.Builder assetDepth(int assetDepth) {
            this.queryParams.put(ASSETDEPTH, String.valueOf(assetDepth));
            return this;
        }

        @Override
        public AssetQuery.Builder all() {
            this.queryParams.put(ASSETDEPTH, "all");
            return this;
        }

        @Override
        public AssetQuery.Builder links(Link... links) {
            Objects.requireNonNull(links, "links must not be null");
            for (Link link : links) {
                this.queryParams.put(LINKS, link.toString());
            }
            return this;
        }

        @Override
        public AssetQuery.Builder profileName(String profileName) {
            Objects.requireNonNull(profileName, "profileName must not be null");
            this.queryParams.put(PROFILENAME, profileName);
            return this;
        }

        @Override
        public AssetQuery.Builder segments(String... segments) {
            Objects.requireNonNull(segments, "segments must not be null");
            for (String segment : segments) {
                this.queryParams.put(SEGMENTS, segment);
            }
            return this;
        }

        @Override
        public AssetQuery build() {
            return new DefaultAssetQuery(queryParams);
        }
    }

}
