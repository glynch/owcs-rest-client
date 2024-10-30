package io.github.glynch.owcs.rest.utils;

import java.util.List;
import java.util.Objects;

import com.fatwire.rest.beans.Parent;

public abstract class ParentUtils {

    private ParentUtils() {
    }

    public static Parent getParent(List<Parent> parents, String parentDefName) {
        Objects.requireNonNull(parents, "parents cannot be null");
        Objects.requireNonNull(parentDefName, "parentDefName cannot be null");
        return parents.stream()
                .filter(p -> parentDefName.equals(p.getParentDefName()))
                .findFirst().orElse(null);
    }

    public static Parent getOrCreateParent(List<Parent> parents, String parentDefName) {
        Objects.requireNonNull(parentDefName, "parentDefName cannot be null");
        Parent parent = getParent(parents, parentDefName);
        if (parent == null) {
            parent = new Parent();
            parent.setParentDefName(parentDefName);
            parents.add(parent);
        }
        return parent;
    }

}
