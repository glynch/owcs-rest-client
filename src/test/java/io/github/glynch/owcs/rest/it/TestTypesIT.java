package io.github.glynch.owcs.rest.it;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.fatwire.rest.beans.AssetTypesBean;
import com.fatwire.rest.beans.Type;

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

    @Test
    void testSubtypes() {
        AssetTypesBean subtypes = restClient.type("AVIArticle").subtypes();
        assertEquals(3, subtypes.getTypes().size());
        Type article = subtypes.getTypes().get(0);
        assertEquals("Article", article.getName());
        Type articleImage = subtypes.getTypes().get(1);
        assertEquals("ArticleImage", articleImage.getName());
        Type image = subtypes.getTypes().get(2);
        assertEquals("Image", image.getName());
    }

    @AfterAll
    void afterAll() {
        jskContainer.stop();
    }

}
