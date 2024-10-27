package io.github.glynch.owcs.rest.client.authenticated.search;

import java.util.Objects;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.lang3.StringUtils;

import io.github.glynch.owcs.rest.support.Fields;

public class DefaultDatabaseSearchQuery extends AbstractAssetQuery implements DatabaseSearchQuery {

    private static final String SEARCH_ENGINE = "searchengine";

    private final Condition[] conditions;

    DefaultDatabaseSearchQuery(int count, int startIndex, SortField sortField, Fields[] fields,
            Condition[] conditions) {
        super(count, startIndex, sortField, fields);
        this.conditions = conditions;
    }

    @Override
    public Condition[] conditions() {
        return conditions;
    }

    @Override
    public MultiValuedMap<String, String> queryParams() {
        MultiValuedMap<String, String> queryParams = super.queryParams();
        if (conditions != null) {
            for (Condition condition : conditions) {
                Operation operation = condition.operation();
                switch (operation) {
                    case CONTAINS:
                    case EQUALS:
                    case PHRASE:
                    case SIMILAR:
                    case STARTS_WITH:
                    case WILDCARD:
                        queryParams.put(condition.toString(), condition.values()[0]);
                        break;
                    case RANGE:
                    case RANGE_INCLUSIVE:
                        queryParams.put(condition.toString(), StringUtils.join(condition.values(), ","));
                        break;
                    default:
                        queryParams.put(condition.toString(), condition.values()[0]);
                        break;
                }

            }
        }
        queryParams.put(SEARCH_ENGINE, SearchEngineType.DBBASIC.toString());

        return queryParams;
    }

    public static class DefaultDatabaseSearchQueryBuilder implements Builder {

        private int startIndex = 0;
        private int count = -1;
        private SortField sortField;
        private Fields[] fields;
        private Condition[] conditions;

        @Override
        public Builder startIndex(int startIndex) {
            this.startIndex = startIndex;
            return this;
        }

        @Override
        public Builder count(int count) {
            this.count = count;
            return this;
        }

        @Override
        public Builder all() {
            return count(-1);
        }

        @Override
        public Builder sortField(SortField sortField) {
            Objects.requireNonNull(sortField, "sortField cannot be null");
            this.sortField = sortField;
            return this;
        }

        @Override
        public Builder fields(Fields... fields) {
            Objects.requireNonNull(fields, "fields cannot be null");
            this.fields = fields;
            return this;
        }

        @Override
        public Builder conditions(Condition... conditions) {
            Objects.requireNonNull(conditions, "conditions cannot be null");
            this.conditions = conditions;
            return this;
        }

        @Override
        public DatabaseSearchQuery build() {
            return new DefaultDatabaseSearchQuery(count, startIndex, sortField, fields, conditions);
        }

    }

}
