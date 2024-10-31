package io.github.glynch.owcs.rest.client.authenticated.search;

import java.util.Arrays;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.lang3.StringUtils;

import io.github.glynch.owcs.rest.client.types.Fields;

public abstract class AbstractAssetQuery implements AssetQuery {

    private final int count;
    private final int startIndex;
    private final SortField sortField;
    private final Fields[] fields;

    AbstractAssetQuery(int count, int startIndex, SortField sortField, Fields... fields) {
        this.count = count;
        this.startIndex = startIndex;
        this.sortField = sortField;
        this.fields = fields;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public int startIndex() {
        return startIndex;
    }

    @Override
    public SortField sortField() {
        return sortField;
    }

    @Override
    public Fields[] fields() {
        return fields;
    }

    @Override
    public MultiValuedMap<String, String> queryParams() {
        MultiValuedMap<String, String> queryParams = new ArrayListValuedHashMap<>();
        if (startIndex >= 0) {
            queryParams.put(START_INDEX, String.valueOf(startIndex));
        }
        if (count >= -1) {
            queryParams.put(COUNT, String.valueOf(count));
        }
        if (fields() != null) {
            queryParams.put(AssetQuery.FIELDS, StringUtils.join(Arrays.asList(fields()), ","));
        }
        if (sortField() != null) {
            queryParams.put(sortField().toString(), null);
        }
        return queryParams;
    }

}
