package io.github.glynch.owcs.rest.support.mixins;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatwire.rest.beans.Site;

public abstract class SitesBeanMixin {

    @JsonProperty("site")
    public abstract List<Site> getSites();
}
