package io.github.glynch.owcs.rest.support;

import java.net.URI;
import java.util.Map;

public interface UriBuilder {

    URI build(Map<String, ?> uriVariables);

    URI build(Object... uriVariables);

}
