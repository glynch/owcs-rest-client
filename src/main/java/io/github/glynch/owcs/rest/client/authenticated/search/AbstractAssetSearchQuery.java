package io.github.glynch.owcs.rest.client.authenticated.search;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.testcontainers.shaded.org.apache.commons.lang3.ArrayUtils;

public abstract class AbstractAssetSearchQuery implements AssetSearchQuery {

    private static final String SEARCHENGINE = "searchengine";
    private static final String FIELDS = "fields";
    private static final String COUNT = "count";
    private static final String STARTINDEX = "startindex";
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
            queryParams.add(STARTINDEX, String.valueOf(startIndex));
        }
        if (count != null) {
            queryParams.add(COUNT, String.valueOf(count));
        }
        if (ArrayUtils.isNotEmpty(fields)) {
            queryParams.add(FIELDS, StringUtils.join(Arrays.asList(fields()), ","));
        }
        if (sortField() != null) {
            queryParams.add(sortField().toString(), null);
        }

        queryParams.add(SEARCHENGINE, searchEngine().toString());
        return queryParams;
    }

}
