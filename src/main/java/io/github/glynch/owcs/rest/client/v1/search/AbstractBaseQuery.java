package io.github.glynch.owcs.rest.client.v1.search;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

public abstract class AbstractBaseQuery implements BaseQuery {

    private final String assetDepth;
    private final Link[] links;
    private final String profileName;
    private final String[] segments;
    private final MultiValuedMap<String, String> queryParams = new ArrayListValuedHashMap<>();

    public AbstractBaseQuery(String assetDepth, Link[] links, String profileName, String[] segments) {
        this.assetDepth = assetDepth;
        this.links = links;
        this.profileName = profileName;
        this.segments = segments;
        if (segments != null && segments.length > 0) {
            for (String segment : segments) {
                this.queryParams.put("segment", segment);
            }
        }
        if (links != null && links.length > 0) {
            for (Link link : links) {
                this.queryParams.put("link", link.toString());
            }
        }
        if (assetDepth != null) {
            this.queryParams.put("assetDepth", assetDepth);
        }
        if (profileName != null) {
            this.queryParams.put("profileName", profileName);
        }
    }

    @Override
    public String assetDepth() {
        return assetDepth;
    }

    @Override
    public Link[] links() {
        return links;
    }

    @Override
    public String profileName() {
        return profileName;
    }

    @Override
    public String[] segments() {
        return segments;
    }

    @Override
    public MultiValuedMap<String, String> queryParams() {
        return queryParams;
    }

}
