package io.github.glynch.owcs.rest.it;

import static io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SITES_URI_TEMPLATE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.fatwire.rest.beans.Site;
import com.fatwire.rest.beans.SiteBean;
import com.fatwire.rest.beans.SitesBean;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClientResponseException;
import io.github.glynch.owcs.test.containers.JSKContainer;

@TestInstance(Lifecycle.PER_CLASS)
public class TestSitesIT {

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

    @Test
    void testSingleSite() {
        SiteBean site = restClient.site("avisports").read();
        assertEquals("avisports", site.getName());
        assertEquals("avisports", site.getDescription());
        assertEquals(34, site.getEnabledAssetTypes().getTypes().size());
    }

    @Test
    void testCreateSite() {
        SiteBean avisports = restClient.site("avisports").read();
        avisports.setId(0L);
        avisports.setName("testsite");
        avisports.setDescription("testsite");
        SiteBean testSite = restClient.site("testsite").create(avisports);
        assertEquals("testsite", testSite.getName());
        assertEquals("testsite", testSite.getDescription());
        assertNotEquals(avisports.getId(), testSite.getId());
    }

    @Test
    void testUpdateSite() {
        SiteBean site = restClient.site("avisports").read();
        String originalDescription = site.getDescription();
        site.setDescription(originalDescription + " UPDATED");
        SiteBean updatedSite = restClient.site("avisports").update(site);
        assertEquals(originalDescription + " UPDATED", updatedSite.getDescription());
    }

    @Test
    void testExceptionForUnknownSite() {
        AuthenticatedRestClientResponseException e = assertThrows(AuthenticatedRestClientResponseException.class,
                () -> restClient.site("FOO").read());
        assertEquals(404, e.getStatusCode());
        assertEquals("Not Found", e.getStatusText());
        assertEquals("Site can not be found in Content Server: FOO",
                e.getError().getMessage());
        assertEquals(0, e.getError().getErrorCode());
    }

    @Test
    void testDeleteSite() {
        SiteBean avisports = restClient.site("avisports").read();
        assertEquals("avisports", avisports.getName());
        restClient.site("avisports").delete();
        AuthenticatedRestClientResponseException e = assertThrows(AuthenticatedRestClientResponseException.class,
                () -> restClient.site("avisports").read());
        assertEquals(404, e.getStatusCode());
        assertEquals("Not Found", e.getStatusText());
        assertEquals("Site can not be found in Content Server: avisports",
                e.getError().getMessage());
        assertEquals(0, e.getError().getErrorCode());

    }

    @AfterEach
    void afterEach() {
        jskContainer.stop();
    }

}
