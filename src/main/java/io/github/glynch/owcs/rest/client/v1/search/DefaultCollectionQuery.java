package io.github.glynch.owcs.rest.client.v1.search;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import io.github.glynch.owcs.rest.support.Fields;
import io.github.glynch.owcs.rest.support.Types;

public class DefaultCollectionQuery extends AbstractBaseQuery implements CollectionQuery {

    public DefaultCollectionQuery(
            MultiValuedMap<String, String> queryParams) {
        super(queryParams);
    }

    @Override
    public Integer limit() {
        return queryParams().get(LIMIT).stream().findFirst().map(Integer::valueOf).orElse(null);
    }

    @Override
    public Integer offset() {
        return queryParams().get(OFFSET).stream().findFirst().map(Integer::valueOf).orElse(null);
    }

    @Override
    public Boolean totalResults() {
        return queryParams().get(TOTAL_RESULTS).stream().findFirst().map(Boolean::valueOf).orElse(null);
    }

    public static class DefaultCollectionQueryBulder implements CollectionQuery.Builder {

        private final MultiValuedMap<String, String> queryParams = new ArrayListValuedHashMap<>();
        AssetQuery.Builder builder = AssetQuery.builder();

        public DefaultCollectionQueryBulder() {
        }

        @Override
        public CollectionQuery.Builder assetDepth(int assetDepth) {
            builder.assetDepth(assetDepth);
            return this;
        }

        @Override
        public CollectionQuery.Builder all() {
            builder.all();
            return this;
        }

        @Override
        public CollectionQuery.Builder links(Link... links) {
            builder.links(links);
            return this;
        }

        @Override
        public Builder expand(Types... types) {
            builder.expand(types);
            return this;
        }

        @Override
        public Builder exclude(Types... types) {
            builder.exclude(types);
            return this;
        }

        @Override
        public Builder fields(Fields... fields) {
            builder.fields(fields);
            return this;
        }

        @Override
        public Builder excludeFields(Fields... fields) {
            builder.excludeFields(fields);
            return this;
        }

        @Override
        public Builder fields(Types type, Fields... fields) {
            builder.fields(type, fields);
            return this;
        }

        @Override
        public Builder excludeFields(Types type, Fields... fields) {
            builder.excludeFields(type, fields);
            return this;
        }

        @Override
        public CollectionQuery.Builder profileName(String profileName) {
            builder.profileName(profileName);
            return this;
        }

        @Override
        public CollectionQuery.Builder segments(String... segments) {
            builder.segments(segments);
            return this;
        }

        @Override
        public CollectionQuery.Builder limit(Integer limit) {
            this.queryParams.put(LIMIT, String.valueOf(limit));
            return this;
        }

        @Override
        public CollectionQuery.Builder offset(Integer offset) {
            this.queryParams.put(OFFSET, String.valueOf(offset));
            return this;
        }

        @Override
        public CollectionQuery.Builder totalResults(Boolean totalResults) {
            this.queryParams.put(TOTAL_RESULTS, String.valueOf(totalResults));
            return this;
        }

        @Override
        public CollectionQuery build() {
            queryParams.putAll(builder.build().queryParams());
            return new DefaultCollectionQuery(queryParams);
        }

    }

}
