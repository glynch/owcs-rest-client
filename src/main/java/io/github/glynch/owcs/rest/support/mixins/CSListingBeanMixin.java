package io.github.glynch.owcs.rest.support.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class CSListingBeanMixin {

    @JsonCreator
    public CSListingBeanMixin(@JsonProperty("type") String type, @JsonProperty("id") String id,
            @JsonProperty("name") String name, @JsonProperty("description") String description,
            @JsonProperty("updateDate") String updateDate, @JsonProperty("site") String site) {
    }
}
