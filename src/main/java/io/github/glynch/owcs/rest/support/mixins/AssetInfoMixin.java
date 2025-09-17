package io.github.glynch.owcs.rest.support.mixins;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatwire.rest.beans.FieldInfo;

public abstract class AssetInfoMixin {

    @JsonProperty("fieldinfo")
    public abstract List<FieldInfo> getFieldinfos();
}
