package io.github.glynch.owcs.rest.support.mixins;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatwire.rest.beans.Role;

public abstract class RolesBeanMixin {

    @JsonProperty("role")
    public abstract List<Role> getRoles();

}
