package io.github.glynch.owcs.rest.client.authenticated.search;

import org.apache.commons.collections4.MultiValuedMap;

public interface SearchQuery {

    MultiValuedMap<String, String> queryParams();

}
