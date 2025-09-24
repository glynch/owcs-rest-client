package io.github.glynch.owcs.rest.it;

import static io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient.SITES_URI_TEMPLATE;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.fatwire.rest.beans.Navigation;
import com.fatwire.rest.beans.NavigationBean;
import com.fatwire.rest.beans.Site;
import com.fatwire.rest.beans.SiteBean;
import com.fatwire.rest.beans.SiteUser;
import com.fatwire.rest.beans.SiteUserBean;
import com.fatwire.rest.beans.SiteUsersBean;
import com.fatwire.rest.beans.SitesBean;

import io.github.glynch.owcs.rest.bean.builders.Builders;
import io.github.glynch.owcs.rest.bean.builders.SiteBeanBuilder;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClientResponseException;
import io.github.glynch.owcs.rest.client.authenticated.NavigationSearch;
import io.github.glynch.owcs.test.containers.JSKContainer;

@TestInstance(Lifecycle.PER_CLASS)
public class TestSiteResourcesIT {

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
        SiteBeanBuilder siteBeanBuilder = Builders.siteBeanBuilder("testsite", "testsite description");
        siteBeanBuilder.type("AVIArticle").type("Page").user("Bill", "SitesUser", "Writer");
        SiteBean testSite = restClient.site("testsite").create(siteBeanBuilder.build());
        assertEquals("testsite", testSite.getName());
        assertEquals("testsite description", testSite.getDescription());
        assertEquals(1, testSite.getSiteUsers().getUsers().size());
        SiteUser siteUser = testSite.getSiteUsers().getUsers().get(0);
        assertEquals("Bill", siteUser.getName());
        List<String> siteRoles = Arrays.asList("SitesUser", "Writer");
        List<String> roles = siteUser.getRoles();
        assertTrue(roles.size() == siteRoles.size() && roles.containsAll(siteRoles) && siteRoles.containsAll(roles));

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

    @Test
    void testHeadSite() {
        SiteBean siteBean = restClient.site("avisports").head();
        // System.out.println("HEAD: " + siteBean.getId());
        // assertEquals("HEAD", siteBean.getName());
    }

    @Test
    void testSiteNavigationByPageId() {
        NavigationBean homeNavigation = restClient.site("avisports").navigation(1327351719456L, "all");
        assertEquals("Home", homeNavigation.getNavigation().getName());
        assertEquals(2, homeNavigation.getNavigation().getNodePath().getNodes().size());
        assertEquals(5, homeNavigation.getNavigation().getPlacedChildren().getchildren().size());
        Navigation surfing = homeNavigation.getNavigation().getPlacedChildren().getchildren().get(0);
        assertEquals("Surfing", surfing.getName());
        assertEquals(1329851332601L, surfing.getId().longValue());
    }

    @Test
    void testSiteNavigationBySearch() {
        NavigationBean navigation = restClient.site("avisports")
                .navigation(NavigationSearch.builder().depth("all").build());
    }

    @Test
    void testSiteUsers() {
        SiteUsersBean siteUsersBean = restClient.site("avisports").users();
        assertEquals(5, siteUsersBean.getUsers().size());
        SiteUser siteUser = siteUsersBean.getUsers().get(0);
        assertEquals("Sally", siteUser.getName());
        assertEquals(Arrays.asList("Writer", "SitesUser"), siteUser.getRoles());
        assertEquals(jskContainer.getRestUrl() + "/sites/avisports/users/Sally", siteUser.getHref());
    }

    @Test
    void testSiteUser() {
        SiteUserBean siteUserBean = restClient.site("avisports").user("Sally");
        assertEquals("Sally", siteUserBean.getName());
        assertEquals(Arrays.asList("Writer", "SitesUser"), siteUserBean.getRoles());
    }

    @AfterEach
    void afterEach() {
        jskContainer.stop();
    }

}
