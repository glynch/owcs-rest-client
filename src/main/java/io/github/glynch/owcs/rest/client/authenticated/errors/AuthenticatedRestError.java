package io.github.glynch.owcs.rest.client.authenticated.errors;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthenticatedRestError(@JsonProperty("errorCode") int errorCode,
        @JsonProperty("message") String message) {

}
