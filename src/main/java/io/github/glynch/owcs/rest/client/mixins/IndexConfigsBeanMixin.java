package io.github.glynch.owcs.rest.client.mixins;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatwire.rest.beans.IndexConfigsBean.IndexConfig;;

public abstract class IndexConfigsBeanMixin {

    @JsonProperty("indexConfig")
    abstract public List<IndexConfig> getIndexConfigs();
}
