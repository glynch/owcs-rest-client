package io.github.glynch.owcs.rest.support.mixins;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatwire.rest.beans.View;

public abstract class ApplicationBeanMixin {

    @JsonProperty("views")
    public abstract List<View> getViews();
}
