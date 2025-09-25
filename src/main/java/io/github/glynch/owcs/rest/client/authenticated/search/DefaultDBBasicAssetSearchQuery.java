package io.github.glynch.owcs.rest.client.authenticated.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.testcontainers.shaded.org.apache.commons.lang3.ArrayUtils;

public class DefaultDBBasicAssetSearchQuery extends AbstractAssetSearchQuery implements DBBasicAssetSearchQuery {

    private final Condition[] conditions;

    public DefaultDBBasicAssetSearchQuery(Condition[] conditions, SortField sortField, int count, int startIndex,
            String[] fields) {
        super(sortField, count, startIndex, fields);
        this.conditions = conditions;
    }

    @Override
    public MultiValueMap<String, String> queryParams() {
        MultiValueMap<String, String> queryParams = super.queryParams();
        if (ArrayUtils.isNotEmpty(conditions)) {
            for (Condition condition : conditions) {
                Operation operation = condition.operation();
                switch (operation) {
                    case CONTAINS:
                    case EQUALS:
                    case PHRASE:
                    case SIMILAR:
                    case STARTS_WITH:
                    case WILDCARD:
                        queryParams.add(condition.toString(), condition.values()[0]);
                        break;
                    case RANGE:
                    case RANGE_INCLUSIVE:
                        queryParams.add(condition.toString(), StringUtils.join(condition.values(), ","));
                        break;
                    default:
                        queryParams.add(condition.toString(), condition.values()[0]);
                        break;
                }

            }
        }

        return queryParams;
    }

    @Override
    public Condition[] conditions() {
        return conditions;
    }

    public static class DBBasicAssetSearchQueryBuilder implements Builder {

        private SortField sortField;
        private int count;
        private int startIndex;
        private List<String> fields = new ArrayList<>();
        private List<Condition> conditions = new ArrayList<>();

        @Override
        public Builder condition(Condition condition) {
            Assert.notNull(condition, "condition cannot be null");
            conditions.add(condition);
            return this;
        }

        @Override
        public Builder sortField(SortField sortField) {
            Assert.notNull(sortField, "sortField cannot be null");
            this.sortField = sortField;
            return this;
        }

        @Override
        public Builder field(String field) {
            Assert.hasText(field, "field cannot be empty or null");
            fields.add(field);
            return this;
        }

        @Override
        public Builder count(int count) {
            this.count = count;
            return this;
        }

        @Override
        public Builder startIndex(int startIndex) {
            this.startIndex = startIndex;
            return this;
        }

        @Override
        public DBBasicAssetSearchQuery build() {
            return new DefaultDBBasicAssetSearchQuery(conditions.toArray(new Condition[0]), sortField, count,
                    startIndex, fields.toArray(new String[0]));
        }

    }

}
