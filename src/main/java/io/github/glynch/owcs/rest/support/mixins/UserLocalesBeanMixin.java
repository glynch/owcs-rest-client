package io.github.glynch.owcs.rest.support.mixins;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatwire.rest.beans.UserLocale;

public abstract class UserLocalesBeanMixin {

    @JsonProperty("userLocale")
    public abstract List<UserLocale> getUserLocales();
}
