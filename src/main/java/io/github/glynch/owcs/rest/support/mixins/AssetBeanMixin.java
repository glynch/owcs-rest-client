package io.github.glynch.owcs.rest.support.mixins;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatwire.rest.beans.Attribute;
import com.fatwire.rest.beans.Parent;

public abstract class AssetBeanMixin {

    @JsonProperty("attribute")
    public abstract List<Attribute> getAttributes();

    @JsonProperty("publist")
    public abstract List<String> getPublists();

    @JsonProperty("parent")
    public abstract List<Parent> getParents();

}
