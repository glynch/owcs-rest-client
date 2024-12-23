package io.github.glynch.owcs.rest.client.v1.search;

import io.github.glynch.owcs.rest.client.types.Fields;
import io.github.glynch.owcs.rest.client.types.Types;
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

        Builder expand(Types... types);

        Builder exclude(Types... types);

        /**
         * Fields to include just for the root asset.
         * 
         * field1,field2,...,fieldN
         * 
         * @param fields
         * @return this
         */
        Builder fields(Fields... fields);

        /**
         * Fields to exclude for the root asset.
         * 
         * !field1,field2,...,fieldN
         * 
         * @param fields
         * @return
         */
        Builder excludeFields(Fields... fields);

        /**
         * Fields to include for the given asset type.
         * 
         * type(field1,field2,...,fieldN)
         * 
         * @param type
         * @param fields
         * @return this
         */
        Builder fields(Types type, Fields... fields);

        /**
         * Fields to exclude for the given asset type.
         * 
         * !type(field1,field2,...,fieldN)
         * 
         * @param type
         * @param fields
         * @return this
         */
        Builder excludeFields(Types type, Fields... fields);

        AssetQuery build();

    }

}
