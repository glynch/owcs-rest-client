package io.github.glynch.owcs.rest.support.mixins;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatwire.rest.beans.EnabledType;

public abstract class EnabledTypesBeanMixin {

    @JsonProperty("type")
    public abstract List<EnabledType> getTypes();
}
