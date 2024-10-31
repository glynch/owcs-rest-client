package io.github.glynch.owcs.rest.client.authenticated.search;

import io.github.glynch.owcs.rest.client.types.Fields;
import io.github.glynch.owcs.rest.client.types.Segments;

public interface RecommendationQuery extends SearchQuery {

    String visitorId();

    Fields[] fields();

    Segments[] segments();

}
