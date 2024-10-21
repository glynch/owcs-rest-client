package io.github.glynch.owcs.rest.client.mixins;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatwire.rest.beans.User;

public abstract class UsersBeanMixin {

    @JsonProperty("user")
    public abstract List<User> getUsers();

}
