package io.github.glynch.owcs.rest.it;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.fatwire.rest.beans.Application;
import com.fatwire.rest.beans.ApplicationBean;
import com.fatwire.rest.beans.ApplicationsBean;
import com.fatwire.rest.beans.LayoutTypeEnum;
import com.fatwire.rest.beans.SiteRoles;
import com.fatwire.rest.beans.View;
import com.fatwire.rest.beans.ViewTypeEnum;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient;
import io.github.glynch.owcs.test.containers.JSKContainer;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestApplicationsIT {

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
    void testApplications() {
        ApplicationsBean applicationsBean = restClient.applications();
        assertEquals(3, applicationsBean.getApplications().size());
        Application application = applicationsBean.getApplications().get(0);
        assertEquals("WEM Admin", application.getName());
        assertEquals("WEM Admin", application.getDescription());
        assertEquals("WEM Admin", application.getShortdescription());
        assertEquals(jskContainer.getRestUrl() + "/applications/1253211458856", application.getHref());
        assertEquals("wemresources/images/icons/apps/Settings.png", application.getIconurl());
        assertEquals("fw.wem.framework.LayoutRenderer", application.getLayouttype());
        assertEquals("WEM Admin", application.getTooltip());
        assertEquals(true, application.isSystemapplication());
    }

    @Test
    void testApplication() {
        ApplicationBean applicationBean = restClient.application(1262707329030L).read();
        assertEquals("Admin", applicationBean.getName());
        assertEquals(1262707329030L, applicationBean.getId());
        assertEquals(LayoutTypeEnum.FW_WEM_FRAMEWORK_LAYOUT_RENDERER, applicationBean.getLayouttype());
        assertEquals("Admin", applicationBean.getDescription());
        assertEquals("wemresources/images/icons/apps/Admin.png", applicationBean.getIconurl());
        assertEquals("Admin", applicationBean.getTooltip());
        assertEquals("wemresources/images/icons/apps/Admin.png", applicationBean.getIconurlhover());
        assertEquals("Admin", applicationBean.getShortdescription());
        assertEquals("wemresources/images/icons/apps/AdminClick.png", applicationBean.getIconurlclick());
        assertEquals(true, applicationBean.isSystemapplication());
        assertEquals(3, applicationBean.getSites().size());
        SiteRoles adminSiteRoles = applicationBean.getSites().get(0);
        assertEquals("AdminSite", adminSiteRoles.getSite());
        assertEquals(Arrays.asList("AdvancedUser"), adminSiteRoles.getRoles());
        SiteRoles avisportsSiteRoles = applicationBean.getSites().get(1);
        assertEquals("avisports", avisportsSiteRoles.getSite());
        assertEquals(Arrays.asList("AdvancedUser"), avisportsSiteRoles.getRoles());
        SiteRoles samplesSiteRoles = applicationBean.getSites().get(2);
        assertEquals("Samples", samplesSiteRoles.getSite());
        assertEquals(Arrays.asList("AdvancedUser"), samplesSiteRoles.getRoles());
        View view = applicationBean.getViews().get(0);
        assertNotNull(view);
        assertEquals(1262707329009L, view.getId());
        assertEquals("Admin", view.getName());
        assertEquals("Admin UI", view.getDescription());
        assertEquals(ViewTypeEnum.FW_WEM_FRAMEWORK_IFRAME_RENDERER, view.getViewtype());
        assertEquals("../../ContentServer?pagename=fatwire/wem/integration/processWemRequest&application=advanced",
                view.getSourceurl());
        assertEquals("", view.getJavascriptcontent());
        assertEquals("", view.getIncludecontent());
    }

    @AfterAll
    void afterAll() {
        jskContainer.stop();
    }

}
