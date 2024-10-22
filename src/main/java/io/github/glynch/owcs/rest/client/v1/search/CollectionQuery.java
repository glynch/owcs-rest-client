package io.github.glynch.owcs.rest.client.v1.search;

import org.apache.commons.collections4.MultiValuedMap;

public interface CollectionQuery {

    int assetDepth();

    int limit();

    int offset();

    String profileName();

    MultiValuedMap<String, String> queryParams();

}
