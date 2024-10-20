package io.github.glynch.owcs.rest.client.mixins;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatwire.rest.beans.Attribute;

public abstract class StructMixin {

    @JsonProperty("item")
    public abstract List<Attribute> getItems();

}
