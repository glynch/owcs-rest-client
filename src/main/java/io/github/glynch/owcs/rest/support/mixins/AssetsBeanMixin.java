package io.github.glynch.owcs.rest.support.mixins;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatwire.rest.beans.AssetInfo;

public abstract class AssetsBeanMixin {

    @JsonProperty("assetinfo")
    public abstract List<AssetInfo> getAssetinfos();

}
