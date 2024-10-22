package io.github.glynch.owcs.rest.client.v1.search;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import io.github.glynch.owcs.rest.support.Fields;
import io.github.glynch.owcs.rest.support.Types;

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
        public Builder expand(Types... types) {
            Objects.requireNonNull(types, "types must not be null");
            for (Types type : types) {
                this.queryParams.put(EXPAND, type.getName());
            }
            return this;
        }

        @Override
        public Builder exclude(Types... types) {
            Objects.requireNonNull(types, "types must not be null");
            for (Types type : types) {
                this.queryParams.put(EXPAND, "!" + type.getName());
            }
            return this;
        }

        @Override
        public Builder fields(Fields... fields) {
            if (fields != null && fields.length > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(Arrays.stream(fields).map(String::valueOf).collect(Collectors.joining(",")));
                this.queryParams.put(FIELDS, sb.toString());
            }

            return this;
        }

        @Override
        public Builder excludeFields(Fields... fields) {
            if (fields != null && fields.length > 0) {
                StringBuilder sb = new StringBuilder("!");
                sb.append(Arrays.stream(fields).map(String::valueOf).collect(Collectors.joining(",")));
                this.queryParams.put(FIELDS, sb.toString());
            }

            return this;
        }

        @Override
        public Builder fields(Types type, Fields... fields) {
            if (type != null && fields != null && fields.length > 0) {
                StringBuilder sb = new StringBuilder(type.getName()).append("(");
                sb.append(Arrays.stream(fields).map(String::valueOf).collect(Collectors.joining(",")));
                sb.append(")");
                this.queryParams.put(FIELDS, sb.toString());
            }
            return this;
        }

        @Override
        public Builder excludeFields(Types type, Fields... fields) {
            if (type != null && fields != null && fields.length > 0) {
                StringBuilder sb = new StringBuilder("!").append(type.getName()).append("(");
                sb.append(Arrays.stream(fields).map(String::valueOf).collect(Collectors.joining(",")));
                sb.append(")");
                this.queryParams.put(FIELDS, sb.toString());
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
