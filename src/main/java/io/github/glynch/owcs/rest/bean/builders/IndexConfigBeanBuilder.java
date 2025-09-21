package io.github.glynch.owcs.rest.bean.builders;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.fatwire.rest.beans.IndexConfigBean;
import com.fatwire.rest.beans.IndexFieldDescriptor;
import com.fatwire.rest.beans.IndexStatus;
import com.fatwire.rest.beans.IndexStatusEnum;

public class IndexConfigBeanBuilder {

    private List<IndexFieldDescriptor> indexFieldDescriptors = new ArrayList<>();
    private List<String> sortableFields = new ArrayList<>();
    private final String name;
    private String uniqueIdField = "id";
    private String defaultSearchField = "DefaultSearchField";
    private String searchEngine = "lucene";
    private boolean indexAllFields = false;

    IndexConfigBeanBuilder(String name) {
        this.name = name;
    }

    public IndexConfigBeanBuilder uniqueIdField(String uniqueIdField) {
        Assert.hasText(uniqueIdField, "uniqueIdField cannot be empty or null");
        this.uniqueIdField = uniqueIdField;
        return this;
    }

    public IndexConfigBeanBuilder defaultSearchField(String defaultSearchField) {
        Assert.hasText(defaultSearchField, "defaultSearchField cannot be empty or null");
        this.defaultSearchField = defaultSearchField;
        return this;
    }

    public IndexConfigBeanBuilder sortableField(String sortableField) {
        Assert.hasText(sortableField, "sortableField cannot be empty or null");
        sortableFields.add(sortableField);
        return this;
    }

    public IndexConfigBeanBuilder indexFieldDescriptor(IndexFieldDescriptor indexFieldDescriptor) {
        Assert.notNull(indexFieldDescriptor, "indexFieldDescriptor cannot be null");
        indexFieldDescriptors.add(indexFieldDescriptor);
        return this;
    }

    public IndexConfigBean build() {
        IndexConfigBean indexConfigBean = new IndexConfigBean();
        indexConfigBean.setName(name);
        indexConfigBean.setUniqueIdField(uniqueIdField);
        indexConfigBean.setDefaultSearchField(defaultSearchField);
        indexConfigBean.setIndexAllFields(indexAllFields);
        indexConfigBean.setSearchEngine(searchEngine);
        for (String sortableField : sortableFields) {
            indexConfigBean.getSortableFields().add(sortableField);
        }
        if (indexFieldDescriptors.size() == 0) {
            throw new IllegalStateException("IndexConfigBean must have at least 1 IndexFieldDescriptor");
        }
        for (IndexFieldDescriptor indexFieldDescriptor : indexFieldDescriptors) {
            indexConfigBean.getFieldDescriptors().add(indexFieldDescriptor);
        }
        IndexStatus indexStatus = new IndexStatus();
        indexStatus.setAssettype(name);
        indexStatus.setIndexStatus(IndexStatusEnum.ENABLED);
        indexConfigBean.getStatusObjects().add(indexStatus);

        return indexConfigBean;
    }

}
