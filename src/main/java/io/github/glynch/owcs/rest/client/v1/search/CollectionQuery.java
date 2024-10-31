package io.github.glynch.owcs.rest.client.v1.search;

import io.github.glynch.owcs.rest.client.types.Fields;
import io.github.glynch.owcs.rest.client.types.Types;
import io.github.glynch.owcs.rest.client.v1.search.DefaultCollectionQuery.DefaultCollectionQueryBulder;

/**
 * See <a href=
 * "https://docs.oracle.com/en/middleware/webcenter/sites/12.2.1.3/wcsrt/op-resources-v1-aggregates-sitename-collectionassettype-assetid-items-get.html">REST
 * API</a>
 */
public interface CollectionQuery extends BaseQuery {

    String LIMIT = "limit";
    String OFFSET = "offset";
    String TOTAL_RESULTS = "totalResults";

    static Builder builder() {
        return new DefaultCollectionQueryBulder();
    }

    Integer limit();

    Integer offset();

    Boolean totalResults();

    interface Builder {

        Builder assetDepth(int assetDepth);

        Builder all();

        Builder links(Link... links);

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

        Builder profileName(String profileName);

        Builder segments(String... segments);

        Builder limit(Integer limit);

        Builder offset(Integer offset);

        Builder totalResults(Boolean totalResults);

        CollectionQuery build();

    }

}
