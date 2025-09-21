package io.github.glynch.owcs.rest.it;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.fatwire.rest.beans.AclsBean;
import com.fatwire.rest.beans.DeviceBean;
import com.fatwire.rest.beans.DeviceGroupBean;
import com.fatwire.rest.beans.Role;
import com.fatwire.rest.beans.RoleBean;
import com.fatwire.rest.beans.RolesBean;
import com.fatwire.rest.beans.TimezoneBean;
import com.fatwire.rest.beans.User;
import com.fatwire.rest.beans.UserBean;
import com.fatwire.rest.beans.UserSite;
import com.fatwire.rest.beans.UsersBean;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient;
import io.github.glynch.owcs.test.containers.JSKContainer;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestMiscIT {

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
    void testTimezone() {
        TimezoneBean timezoneBean = restClient.timezone();
        assertEquals("UTC", timezoneBean.getId());
        assertEquals("Coordinated Universal Time", timezoneBean.getDisplayName());
        assertEquals(false, timezoneBean.isUserDaylightTime());
    }

    @Test
    void testCurrentDevice() {
        DeviceBean deviceBean = restClient.currentDevice();
        assertEquals("Unknown device", deviceBean.getName());
        assertEquals(1, deviceBean.getDeviceGroupBean().size());
        DeviceGroupBean deviceGroupBean = deviceBean.getDeviceGroupBean().get(0);
        assertEquals("Desktop", deviceGroupBean.getName());
        assertEquals("", deviceGroupBean.getSuffix());
        assertEquals(9999, deviceGroupBean.getPriority().intValue());
    }

    @Test
    void testAcls() {
        AclsBean aclsBean = restClient.acls();
        assertEquals(18, aclsBean.getAcls().size());
        assertEquals("Browser", aclsBean.getAcls().get(0));
    }

    @Test
    void testUsers() {
        UsersBean usersBean = restClient.users();
        assertEquals(7, usersBean.getTotal().intValue());
        assertEquals(0, usersBean.getStartindex().intValue());
        User user = usersBean.getUsers().get(0);
        assertEquals("Bill", user.getName());
        assertEquals(jskContainer.getRestUrl() + "/users/Bill", user.getHref());
    }

    @Test
    void testSingleUser() {
        List<String> acls = Arrays.asList("Browser", "ElementReader", "PageReader", "UserReader", "Visitor",
                "xceleditor");
        List<String> roles = Arrays.asList("SitesUser", "Reviewer");
        UserBean userBean = restClient.user("Bill");
        UserSite userSite = userBean.getSites().get(0);
        assertEquals("Bill", userBean.getName());
        assertEquals("userid=1502442347337,ou=People", userBean.getId());
        assertEquals(acls, userBean.getAcls());
        assertEquals("avisports", userSite.getSite());
        assertEquals(roles, userSite.getRoles());
    }

    @Test
    void testRoles() {
        RolesBean rolesBean = restClient.roles();
        assertEquals(13, rolesBean.getTotal().intValue());
        Role role = rolesBean.getRoles().get(0);
        assertEquals("AdvancedUser", role.getName());
        assertEquals("Advanced User", role.getDescription());
        assertEquals(jskContainer.getRestUrl() + "/roles/AdvancedUser", role.getHref());
    }

    @Test
    void testSingleRole() {
        RoleBean roleBean = restClient.role("AdvancedUser");
        assertEquals("AdvancedUser", roleBean.getName());
        assertEquals("Advanced User", roleBean.getDescription());
    }

    @AfterEach
    void afterEach() {
        jskContainer.stop();
    }

}
