package io.github.glynch.owcs.rest.bean.builders;

import com.fatwire.rest.beans.IndexFieldDescriptor;
import com.fatwire.rest.beans.IndexFieldTypeEnum;

public class IndexFieldDescriptorBuilder {

    private final IndexFieldDescriptor indexFieldDescriptor;

    IndexFieldDescriptorBuilder(String name, IndexFieldTypeEnum type) {
        this.indexFieldDescriptor = new IndexFieldDescriptor();
        indexFieldDescriptor.setName(name);
        indexFieldDescriptor.setType(type);
        indexFieldDescriptor.setStored(false);
        indexFieldDescriptor.setTokenized(false);
        indexFieldDescriptor.setBoost(100);
    }

    public IndexFieldDescriptorBuilder boost(int boost) {
        indexFieldDescriptor.setBoost(boost);
        return this;
    }

    public IndexFieldDescriptorBuilder stored() {
        indexFieldDescriptor.setStored(true);
        return this;
    }

    public IndexFieldDescriptorBuilder tokenized() {
        indexFieldDescriptor.setTokenized(true);
        return this;
    }

    public IndexFieldDescriptor build() {
        return indexFieldDescriptor;
    }

}
