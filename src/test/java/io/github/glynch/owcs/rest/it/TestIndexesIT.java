package io.github.glynch.owcs.rest.it;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.fatwire.rest.beans.IndexConfigBean;
import com.fatwire.rest.beans.IndexConfigsBean;
import com.fatwire.rest.beans.IndexConfigsBean.IndexConfig;
import com.fatwire.rest.beans.IndexFieldDescriptor;
import com.fatwire.rest.beans.IndexFieldTypeEnum;
import com.fatwire.rest.beans.IndexStatus;
import com.fatwire.rest.beans.IndexStatusEnum;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient;
import io.github.glynch.owcs.test.containers.JSKContainer;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestIndexesIT {

    private JSKContainer jskContainer = new JSKContainer("grahamlynch/jsk:12.2.1.3.0-samples");
    private AuthenticatedRestClient restClient;

    @BeforeEach
    void beforeEach() {
        jskContainer.start();
        restClient = AuthenticatedRestClient.builder(jskContainer.getBaseUrl(), "fwadmin", "xceladmin")
                .cachingTokenProvider()
                .build();
    }

    @Test
    void testIndexes() {
        IndexConfigsBean indexConfigsBean = restClient.indexes();
        assertEquals(5, indexConfigsBean.getIndexConfigs().size());
        IndexConfig indexConfig = indexConfigsBean.getIndexConfigs().get(0);
        assertEquals("ContentCloud", indexConfig.getName());
        assertEquals(jskContainer.getRestUrl() + "/indexes/ContentCloud", indexConfig.getHref());
        IndexStatus indexStatus = indexConfig.getStatusObjects().get(0);
        assertEquals("ContentCloud", indexStatus.getAssettype());
        assertEquals(IndexStatusEnum.ENABLED, indexStatus.getIndexStatus());
    }

    @Test
    void testSingleIndex() {
        IndexConfigBean indexConfigBean = restClient.index("ContentCloud").read();
        assertEquals("ContentCloud", indexConfigBean.getName());
        assertEquals("DefaultSearchField", indexConfigBean.getDefaultSearchField());
        assertEquals("id", indexConfigBean.getUniqueIdField());
        assertEquals("lucene", indexConfigBean.getSearchEngine());
        assertEquals(Collections.singletonList("name"), indexConfigBean.getSortableFields());
        assertEquals(false, indexConfigBean.isIndexAllFields());
        assertEquals(7, indexConfigBean.getFieldDescriptors().size());
        IndexFieldDescriptor indexFieldDescriptor = indexConfigBean.getFieldDescriptors().get(0);
        assertEquals("thumbnail", indexFieldDescriptor.getName());
        assertEquals(IndexFieldTypeEnum.TEXT, indexFieldDescriptor.getType());
        assertEquals(true, indexFieldDescriptor.isStored());
        assertEquals(true, indexFieldDescriptor.isTokenized());
        assertEquals(100, indexFieldDescriptor.getBoost());
        assertEquals(1, indexConfigBean.getStatusObjects().size());
        IndexStatus indexStatus = indexConfigBean.getStatusObjects().get(0);
        assertEquals("ContentCloud", indexStatus.getAssettype());
        assertEquals(IndexStatusEnum.ENABLED, indexStatus.getIndexStatus());
    }

    @Test
    void testCreateIndex() {

    }

    @Test
    void testUpdateIndex() {
        IndexConfigBean indexConfigBean = restClient.index("AVIArticle").read();
    }

    @AfterAll
    void afterAll() {
        jskContainer.stop();
    }

}
