package io.github.glynch.owcs.rest.bean.builders;

import com.fatwire.rest.beans.IndexFieldTypeEnum;

public class Builders {

    public static IndexConfigBeanBuilder indexConfigBeanBuilder(String name) {
        return new IndexConfigBeanBuilder(name);
    }

    public static IndexFieldDescriptorBuilder indexFieldDescriptorBuilder(String name, IndexFieldTypeEnum type) {
        return new IndexFieldDescriptorBuilder(name, type);
    }

}
