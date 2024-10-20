package io.github.glynch.owcs.rest.client.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RestError(@JsonProperty("errorCode") int errorCode, @JsonProperty("message") String message) {

}
