package io.github.glynch.owcs.rest.it;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Association;
import com.fatwire.rest.beans.AssociationBean;
import com.fatwire.rest.beans.AssociationBean.AssociatedAsset;
import com.fatwire.rest.beans.AssociationsBean;
import com.fatwire.rest.beans.Webreference;

import io.github.glynch.owcs.rest.bean.AssetBeanFacade;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient;
import io.github.glynch.owcs.test.containers.JSKContainer;

@TestInstance(Lifecycle.PER_CLASS)
public class TestSiteTypeAssetResourcesIT {

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
    void testReadAsset() {
        AssetBean assetBean = restClient.site("avisports").type("AVIArticle").asset(1328196047309L).read();
        AssetBeanFacade facade = new AssetBeanFacade(assetBean);
        assertEquals("AVIArticle:1328196047309", facade.getId());
        assertEquals("AVIArticle", facade.getAssetId().getType());
        assertEquals(1328196047309L, facade.getAssetId().getId());
        assertEquals("Rookie Skier Makes Her Mark ", facade.getName());
        assertEquals("", facade.getDescription());
        assertEquals("Article", facade.getSubtype());
        assertEquals(Arrays.asList("avisports"), facade.getSites());
        assertEquals("ArticleLayout", facade.getTemplate());
        assertEquals("fwadmin", facade.getCreatedBy());
        assertEquals("fwadmin", facade.getUpdatedBy());
        assertEquals("e0df548b-3acd-466e-911b-d29e97535643", facade.getFwUid());
        assertEquals("Rookie Skier Makes Mark at Winter X Games", facade.getStringAttribute("headline"));
        List<Webreference> webReferences = facade.getWebReferences();
        Webreference webReference = webReferences.get(0);
        assertEquals("http://localhost:7003/sites/avi/article/rookie_skier_makes_her_mark_.html",
                webReference.getHref());
        assertEquals(200, webReference.getHttpstatus());
        assertEquals("avisports/AVIArticle/ArticleLayout", webReference.getTemplate());
        assertEquals("Avi", webReference.getWebroot());

    }

    @Test
    void testAssetAssociations() {
        AssociationsBean associationsBean = restClient.site("avisports").type("Page").asset(1327351719380L)
                .associations();
        List<Association> associations = associationsBean.getAssociations();
        assertEquals(2, associations.size());
        Association association = associations.get(0);
        List<String> associatedAssets = association.getAssociatedAssets();
        assertEquals(4, associatedAssets.size());
        assertEquals(Arrays.asList("AVIArticle:1328196049021", "AVIArticle:1328196048989", "AVIArticle:1328196049037",
                "AVIArticle:1329851315961"), associatedAssets);
        assertEquals(
                jskContainer.getRestUrl()
                        + "/sites/avisports/types/Page/assets/1327351719380/associations/contentList1",
                association.getHref());
        assertEquals("contentList1", association.getName());

    }

    @Test
    void testAssetAssociation() {

        AssociationBean associationBean = restClient.site("avisports").type("Page").asset(1327351719380L)
                .association("contentList1");
        assertEquals("contentList1", associationBean.getName());
        assertEquals(4, associationBean.getAssociatedAssets().size());
        AssociatedAsset associatedAsset = associationBean.getAssociatedAssets().get(0);
        assertEquals("Behind the Hype of Professional Soccer Premier Event", associatedAsset.getName());
        assertEquals("", associatedAsset.getDescription());
        assertEquals("AVIArticle:1328196049021", associatedAsset.getId());

    }

    @AfterEach
    void afterEach() {
        jskContainer.stop();
    }

}
