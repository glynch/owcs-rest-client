package io.github.glynch.owcs.rest.client.v1.search;

import org.apache.commons.collections4.MultiValuedMap;

public interface BaseQuery {

    String assetDepth();

    Link[] links();

    String profileName();

    String[] segments();

    MultiValuedMap<String, String> queryParams();

}
