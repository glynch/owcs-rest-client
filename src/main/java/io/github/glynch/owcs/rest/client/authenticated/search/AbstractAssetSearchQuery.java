package io.github.glynch.owcs.rest.client.authenticated.search;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public abstract class AbstractAssetSearchQuery implements AssetSearchQuery {

    private final SortField sortField;
    private final Integer count;
    private final Integer startIndex;
    private final String[] fields;

    public AbstractAssetSearchQuery(SortField sortField, Integer count, Integer startIndex, String[] fields) {
        this.sortField = sortField;
        this.count = count;
        this.startIndex = startIndex;
        this.fields = fields;
    }

    @Override
    public SortField sortField() {
        return sortField;
    }

    @Override
    public String[] fields() {
        return fields;
    }

    @Override
    public Integer count() {
        return count;
    }

    @Override
    public Integer startIndex() {
        return startIndex;
    }

    @Override
    public MultiValueMap<String, String> queryParams() {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        if (startIndex != null) {
            queryParams.add("startindex", String.valueOf(startIndex));
        }
        if (count != null) {
            queryParams.add("count", String.valueOf(count));
        }
        if (fields() != null) {
            queryParams.add("fields", StringUtils.join(Arrays.asList(fields()), ","));
        }
        if (sortField() != null) {
            queryParams.add(sortField().toString(), null);
        }

        queryParams.add("searchengine", searchEngine().toString());
        return queryParams;
    }

}
