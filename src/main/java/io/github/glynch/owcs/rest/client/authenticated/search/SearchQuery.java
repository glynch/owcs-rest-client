package io.github.glynch.owcs.rest.client.authenticated.search;

import org.springframework.util.MultiValueMap;

public interface SearchQuery {

    MultiValueMap<String, String> queryParams();

}