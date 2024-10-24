package io.github.glynch.owcs.rest.client.mixins;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class ParentMixin {

    @JsonProperty("asset")
    public abstract List<String> getAssets();

}
