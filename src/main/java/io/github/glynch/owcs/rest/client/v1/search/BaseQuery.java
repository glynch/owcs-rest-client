package io.github.glynch.owcs.rest.client.v1.search;

import org.apache.commons.collections4.MultiValuedMap;

public interface BaseQuery {

    String ASSETDEPTH = "assetDepth";
    String LINKS = "links";
    String PROFILENAME = "profileName";
    String SEGMENTS = "segments";
    String FIELDS = "fields";
    String EXPAND = "expand";

    String assetDepth();

    Link[] links();

    String profileName();

    String[] segments();

    String[] expand();

    MultiValuedMap<String, String> queryParams();

}
