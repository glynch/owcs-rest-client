package io.github.glynch.owcs.rest.client.v1.search;

public interface CollectionQuery {

    int assetDepth();

    int limit();

    int offset();

    String profileName();

}
