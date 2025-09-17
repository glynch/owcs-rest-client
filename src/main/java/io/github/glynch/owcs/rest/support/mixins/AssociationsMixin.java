package io.github.glynch.owcs.rest.support.mixins;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatwire.rest.beans.Association;

public abstract class AssociationsMixin {

    @JsonProperty("association")
    public abstract List<Association> getAssociations();

}
