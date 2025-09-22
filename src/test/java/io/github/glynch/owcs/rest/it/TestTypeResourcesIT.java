package io.github.glynch.owcs.rest.it;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.fatwire.rest.beans.AssetTypeBean;
import com.fatwire.rest.beans.AssetTypesBean;
import com.fatwire.rest.beans.AttributeDefBean;
import com.fatwire.rest.beans.AttributeTypeEnum;
import com.fatwire.rest.beans.Type;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClientResponseException;
import io.github.glynch.owcs.test.containers.JSKContainer;

@TestInstance(Lifecycle.PER_CLASS)
public class TestTypeResourcesIT {

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
    void testTypes() {
        AssetTypesBean assetTypesBean = restClient.types();
        assertEquals(57, assetTypesBean.getTypes().size());
        assertEquals("AVIArticle", assetTypesBean.getTypes().get(0).getName());
        assertEquals(3, assetTypesBean.getTypes().get(0).getSubtypes().size());
    }

    @Test
    void testSingleType() {
        AssetTypeBean type = restClient.type("AVIArticle").read();
        assertEquals("AVIArticle", type.getName());
        assertEquals("Article", type.getDescription());
        assertEquals(true, type.isCanBeChild());
        assertEquals(false, type.isProxyAsset());
        assertEquals(25, type.getAttributes().size());

    }

    @Test
    void testCreateType() {
        AssetTypeBean type = restClient.type("AVIArticle").read();
        type.setName("AVITestType");
        type.setDescription("AVITestType description");
        type.setCanBeChild(false);
        type.setSubtype("TestType");
        AssetTypeBean testType = restClient.type("AVITestType").create(type);
        assertEquals("AVITestType", testType.getName());
        assertEquals("AVITestType description", testType.getDescription());
        assertEquals(false, testType.isCanBeChild());
        assertEquals("TestType", testType.getSubtype());

    }

    @Test
    void testTypeSubtypes() {
        AssetTypesBean subtypes = restClient.type("AVIArticle").subtypes();
        assertEquals(3, subtypes.getTypes().size());
        Type article = subtypes.getTypes().get(0);
        assertEquals("Article", article.getName());
        Type articleImage = subtypes.getTypes().get(1);
        assertEquals("ArticleImage", articleImage.getName());
        Type image = subtypes.getTypes().get(2);
        assertEquals("Image", image.getName());

    }

    @Test
    void testSingleSubtype() {
        AssetTypeBean subtype = restClient.type("AVIArticle").subtype("Article");
        assertEquals("AVIArticle", subtype.getName());
        assertEquals("Article", subtype.getSubtype());
        assertEquals("Article", subtype.getDescription());
        assertEquals(true, subtype.isCanBeChild());
        assertEquals(false, subtype.isProxyAsset());
        assertEquals(36, subtype.getAttributes().size());
        AttributeDefBean attributeDef = subtype.getAttributes().get(0);
        assertEquals("name", attributeDef.getName());
        assertEquals("Name", attributeDef.getDescription());
        assertEquals(AttributeTypeEnum.STRING, attributeDef.getType());

    }

    @Test
    void testDeleteType() {
        // First create a type to delete
        AssetTypeBean type = restClient.type("AVIArticle").read();
        type.setName("AVITestTypeToDelete");
        type.setDescription("AVITestTypeToDelete description");
        type.setSubtype("TestTypeToDelete");
        AssetTypeBean testType = restClient.type("AVITestTypeToDelete").create(type);
        assertEquals("AVITestTypeToDelete", testType.getName());
        restClient.type("AVITestTypeToDelete").delete();
        AuthenticatedRestClientResponseException e = assertThrows(AuthenticatedRestClientResponseException.class,
                () -> restClient.type("AVITestTypeToDelete").read());
        assertEquals(404, e.getStatusCode());
        assertEquals("Not Found", e.getStatusText());
        assertEquals("Asset type AVITestTypeToDelete does not exist in Content Server", e.getError().getMessage());
        assertEquals(0, e.getError().getErrorCode());
    }

    @Test
    void testExceptionForUnknownType() {
        AuthenticatedRestClientResponseException e = assertThrows(AuthenticatedRestClientResponseException.class,
                () -> restClient.type("FOO").subtypes());
        assertEquals(404, e.getStatusCode());
        assertEquals("Not Found", e.getStatusText());
        assertEquals("Asset type FOO does not exist in Content Server", e.getError().getMessage());
        assertEquals(0, e.getError().getErrorCode());
    }

    @AfterEach
    void afterEach() {
        jskContainer.stop();
    }

}
