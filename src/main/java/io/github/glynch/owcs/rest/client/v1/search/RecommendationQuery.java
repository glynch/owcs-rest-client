package io.github.glynch.owcs.rest.client.v1.search;

import io.github.glynch.owcs.rest.client.types.Fields;
import io.github.glynch.owcs.rest.client.types.Types;
import io.github.glynch.owcs.rest.client.v1.search.DefaultRecommendationQuery.DefaultRecommendationQueryBuilder;

public interface RecommendationQuery extends CollectionQuery {

    String VISITOR_ID = "visitorId";

    static Builder builder() {
        return new DefaultRecommendationQueryBuilder();
    }

    Long visitorId();

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

        Builder visitorId(Long visitorId);

        RecommendationQuery build();
    }

}
