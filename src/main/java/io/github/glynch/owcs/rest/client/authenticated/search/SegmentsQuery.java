package io.github.glynch.owcs.rest.client.authenticated.search;

import io.github.glynch.owcs.rest.client.types.Segments;

public interface SegmentsQuery {

    String VISITOR_ID = "visitorid";
    String SEGMENTS = "segments";

    String visitorId();

    Segments[] segments();
}
