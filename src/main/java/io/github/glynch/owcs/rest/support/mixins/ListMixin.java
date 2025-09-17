package io.github.glynch.owcs.rest.support.mixins;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatwire.rest.beans.Struct;

public abstract class ListMixin {

    @JsonProperty("item")
    public abstract List<Struct> getItems();

}
