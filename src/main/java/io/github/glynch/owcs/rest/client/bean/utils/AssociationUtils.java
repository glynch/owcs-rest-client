package io.github.glynch.owcs.rest.client.bean.utils;

import java.util.List;
import java.util.Objects;

import com.fatwire.rest.beans.Association;

public abstract class AssociationUtils {

    private AssociationUtils() {
    }

    public static Association getAssociation(List<Association> associations, String name) {
        Objects.requireNonNull(associations, "associations cannot be null");
        Objects.requireNonNull(name, "name cannot be null");
        return associations.stream()
                .filter(a -> name.equals(a.getName()))
                .findFirst()
                .orElse(null);
    }

    public static Association getOrCreateAssociation(List<Association> associations, String name) {
        Objects.requireNonNull(name, "name cannot be null");
        Association association = getAssociation(associations, name);
        if (association == null) {
            association = new Association();
            association.setName(name);
            associations.add(association);
        }
        return association;
    }

}
