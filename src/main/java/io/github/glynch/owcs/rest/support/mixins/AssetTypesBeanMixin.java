package io.github.glynch.owcs.rest.support.mixins;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatwire.rest.beans.Type;

public abstract class AssetTypesBeanMixin {

    @JsonProperty("type")
    public abstract List<Type> getTypes();
}
