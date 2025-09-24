package io.github.glynch.owcs.rest.bean.builders;

import com.fatwire.rest.beans.IndexFieldDescriptor;
import com.fatwire.rest.beans.IndexFieldTypeEnum;

public class IndexFieldDescriptorBuilder {

    private final String name;
    private final IndexFieldTypeEnum type;
    private int boost = 100;
    private boolean isTokenized = false;
    private boolean isStored = false;

    IndexFieldDescriptorBuilder(String name, IndexFieldTypeEnum type) {
        this.name = name;
        this.type = type;
    }

    public IndexFieldDescriptorBuilder boost(int boost) {
        this.boost = boost;
        return this;
    }

    public IndexFieldDescriptorBuilder stored() {
        this.isStored = true;
        return this;
    }

    public IndexFieldDescriptorBuilder tokenized() {
        this.isTokenized = true;
        return this;
    }

    public IndexFieldDescriptor build() {
        IndexFieldDescriptor indexFieldDescriptor = new IndexFieldDescriptor();
        indexFieldDescriptor.setName(name);
        indexFieldDescriptor.setType(type);
        indexFieldDescriptor.setStored(isStored);
        indexFieldDescriptor.setTokenized(isTokenized);
        indexFieldDescriptor.setBoost(boost);
        return indexFieldDescriptor;
    }

}
