package io.github.glynch.owcs.rest.support;

import java.net.URI;
import java.util.Map;

import org.apache.commons.collections4.MultiValuedMap;

public interface UriBuilder {

    static UriBuilder builder(String uriTemmplate) {
        return new DefaultUriBuilder(uriTemmplate);
    }

    URI build(Map<String, ?> uriVariables);

    URI build(Object... uriVariables);

    UriBuilder encode();

    UriBuilder queryParam(String name, Object... values);

    UriBuilder queryParams(MultiValuedMap<String, String> params);

}
