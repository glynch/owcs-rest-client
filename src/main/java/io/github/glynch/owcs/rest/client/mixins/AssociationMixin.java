package io.github.glynch.owcs.rest.client.mixins;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AssociationMixin {

    @JsonProperty("associatedAsset")
    public abstract List<String> getAssociatedAssets();
}
