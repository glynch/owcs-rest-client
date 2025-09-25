package io.github.glynch.owcs.rest.client.authenticated.search;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.MultiValueMap;

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

}
