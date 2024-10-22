package io.github.glynch.owcs.rest.client.v1.search;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

public abstract class AbstractBaseQuery implements BaseQuery {

    private final MultiValuedMap<String, String> queryParams = new ArrayListValuedHashMap<>();

    public AbstractBaseQuery(MultiValuedMap<String, String> queryParams) {
        this.queryParams.putAll(queryParams);
    }

    @Override
    public String assetDepth() {
        return queryParams.get(ASSETDEPTH).stream().findFirst().orElse(null);
    }

    @Override
    public Link[] links() {
        return queryParams.get(LINKS).stream().map(Link::of).toArray(Link[]::new);
    }

    @Override
    public String profileName() {
        return queryParams.get(PROFILENAME).stream().findFirst().orElse(null);
    }

    @Override
    public String[] segments() {
        return queryParams.get(SEGMENTS).toArray(new String[0]);
    }

    @Override
    public String[] expand() {
        return queryParams.get(EXPAND).toArray(new String[0]);
    }

    @Override
    public MultiValuedMap<String, String> queryParams() {
        return queryParams;
    }

}
