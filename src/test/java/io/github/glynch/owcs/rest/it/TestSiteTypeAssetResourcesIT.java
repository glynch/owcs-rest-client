package io.github.glynch.owcs.rest.it;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.fatwire.rest.beans.AssetBean;
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
        System.out.println(facade.getId());
        System.out.println(facade.getAssetId().getType());
        System.out.println(facade.getAssetId().getId());
        System.out.println(facade.getName());
        System.out.println(facade.getDescription());
        System.out.println(facade.getSubtype());
        System.out.println(facade.getSites());
        System.out.println(facade.getTemplate());
        System.out.println(facade.getStartDate());
        System.out.println(facade.getCreatedBy());
        System.out.println(facade.getUpdatedBy());
        System.out.println(facade.getFwTags());
        System.out.println(facade.getFwUid());
        System.out.println(facade.getStringAttribute("body"));
        List<Webreference> webReferences = facade.getWebReferences();
        for (Webreference webreference : webReferences) {
            System.out.println(webreference.getHref());
        }
        facade.setStringAttribute("body", "mybody");
        facade.setStringAttribute("testattribute", "Test value");
        System.out.println(facade.getStringAttribute("body"));
        System.out.println(facade.getStringAttribute("testattribute"));

    }

    @AfterEach
    void afterEach() {
        jskContainer.stop();
    }

}
