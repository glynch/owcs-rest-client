package io.github.glynch.owcs.rest.bean.builders;

import org.springframework.util.Assert;

import com.fatwire.rest.beans.IndexConfigBean;
import com.fatwire.rest.beans.IndexFieldDescriptor;
import com.fatwire.rest.beans.IndexStatus;
import com.fatwire.rest.beans.IndexStatusEnum;

public class IndexConfigBeanBuilder {

    private final IndexConfigBean indexConfigBean;

    IndexConfigBeanBuilder(String name) {
        indexConfigBean = new IndexConfigBean();
        indexConfigBean.setName(name);
        indexConfigBean.setSearchEngine("lucene");
        indexConfigBean.setDefaultSearchField("DefaultSearchField");
        indexConfigBean.setUniqueIdField("id");
        indexConfigBean.setIndexAllFields(false);
        indexConfigBean.getSortableFields().add("name");
        IndexStatus indexStatus = new IndexStatus();
        indexStatus.setAssettype(name);
        indexStatus.setIndexStatus(IndexStatusEnum.ENABLED);
        indexConfigBean.getStatusObjects().add(indexStatus);
    }

    public IndexConfigBeanBuilder uniqueIdField(String uniqueIdField) {
        Assert.hasText(uniqueIdField, "uniqueIdField cannot be empty or null");
        indexConfigBean.setUniqueIdField(uniqueIdField);
        return this;
    }

    public IndexConfigBeanBuilder defaultSearchField(String defaultSearchField) {
        Assert.hasText(defaultSearchField, "defaultSearchField cannot be empty or null");
        indexConfigBean.setDefaultSearchField(defaultSearchField);
        return this;
    }

    public IndexConfigBeanBuilder sortableField(String sortableField) {
        Assert.hasText(sortableField, "sortableField cannot be empty or null");
        indexConfigBean.getSortableFields().add(sortableField);
        return this;
    }

    public IndexConfigBeanBuilder indexFieldDescriptor(IndexFieldDescriptor indexFieldDescriptor) {
        Assert.notNull(indexFieldDescriptor, "indexFieldDescriptor cannot be null");
        indexConfigBean.getFieldDescriptors().add(indexFieldDescriptor);
        return this;
    }

    public IndexConfigBeanBuilder indexAllFields() {
        indexConfigBean.setIndexAllFields(true);
        return this;
    }

    public IndexConfigBean build() {
        if (indexConfigBean.getFieldDescriptors().size() == 0) {
            throw new IllegalStateException("IndexConfigBean must have at least 1 IndexFieldDescriptor");
        }
        return indexConfigBean;
    }

}
