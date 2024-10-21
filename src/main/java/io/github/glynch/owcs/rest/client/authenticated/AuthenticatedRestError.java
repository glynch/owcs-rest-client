package io.github.glynch.owcs.rest.client.authenticated;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthenticatedRestError(@JsonProperty("errorCode") int errorCode,
        @JsonProperty("message") String message) {

}
