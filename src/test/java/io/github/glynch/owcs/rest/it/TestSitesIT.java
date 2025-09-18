package io.github.glynch.owcs.rest.it;

import static io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SITES_URI_TEMPLATE;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.fatwire.rest.beans.Site;
import com.fatwire.rest.beans.SitesBean;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient;
import io.github.glynch.owcs.test.containers.JSKContainer;

@TestInstance(Lifecycle.PER_CLASS)

public class TestSitesIT {

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
    void testSites() {
        SitesBean sitesBean = restClient.sites();
        assertEquals(sitesBean.getTotal().intValue(), 3);
        Site adminSite = sitesBean.getSites().get(0);
        assertEquals("AdminSite", adminSite.getName());
        assertEquals("AdminSite", adminSite.getDescription());
        assertEquals(jskContainer.getRestUrl() + SITES_URI_TEMPLATE + "/AdminSite", adminSite.getHref());
        Site avisports = sitesBean.getSites().get(1);
        assertEquals("avisports", avisports.getName());
        assertEquals("avisports", avisports.getDescription());
        assertEquals(jskContainer.getRestUrl() + SITES_URI_TEMPLATE + "/avisports", avisports.getHref());
        Site samples = sitesBean.getSites().get(2);
        assertEquals("Samples", samples.getName());
        assertEquals("A site containing code samples for using Controller asset.", samples.getDescription());
        assertEquals(jskContainer.getRestUrl() + SITES_URI_TEMPLATE + "/Samples", samples.getHref());

    }

    @AfterAll
    void afterAll() {
        jskContainer.stop();
    }

}
