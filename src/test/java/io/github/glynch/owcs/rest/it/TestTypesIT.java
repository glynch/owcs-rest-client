package io.github.glynch.owcs.rest.it;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.fatwire.rest.beans.AssetTypesBean;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient;
import io.github.glynch.owcs.test.containers.JSKContainer;

@TestInstance(Lifecycle.PER_CLASS)

public class TestTypesIT {

    private JSKContainer jskContainer;
    private AuthenticatedRestClient restClient;

    @BeforeAll
    void beforeAll() {
        jskContainer = new JSKContainer("grahamlynch/jsk:12.2.1.3.0-samples");
        jskContainer.start();
        restClient = AuthenticatedRestClient.builder(jskContainer.getBaseUrl(), "fwadmin", "xceladmin")
                .cachingTokenProvider()
                .build();
    }

    @Test
    void testTypes() {
        AssetTypesBean assetTypesBean = restClient.types();
        assertEquals(57, assetTypesBean.getTypes().size());
        assertEquals("AVIArticle", assetTypesBean.getTypes().get(0).getName());
        assertEquals(3, assetTypesBean.getTypes().get(0).getSubtypes().size());
    }

    @AfterAll
    void afterAll() {
        jskContainer.stop();
    }

}
