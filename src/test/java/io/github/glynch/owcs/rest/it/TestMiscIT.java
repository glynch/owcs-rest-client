package io.github.glynch.owcs.rest.it;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.fatwire.rest.beans.AclsBean;
import com.fatwire.rest.beans.DeviceBean;
import com.fatwire.rest.beans.DeviceGroupBean;
import com.fatwire.rest.beans.Group;
import com.fatwire.rest.beans.GroupBean;
import com.fatwire.rest.beans.GroupsBean;
import com.fatwire.rest.beans.TimezoneBean;
import com.fatwire.rest.beans.UserAttributeDefBean;
import com.fatwire.rest.beans.UserDefBean;
import com.fatwire.rest.beans.UserLocale;
import com.fatwire.rest.beans.UserLocalesBean;

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
    void testGroups() {
        GroupsBean groupsBean = restClient.groups();
        assertEquals(2, groupsBean.getGroups().size());
        Group group = groupsBean.getGroups().get(0);
        assertEquals("RestAdmin", group.getName());
        assertEquals("Rest Admin", group.getDescription());
        assertEquals(jskContainer.getRestUrl() + "/groups/RestAdmin", group.getHref());
    }

    @Test
    void testSingleGroup() {
        GroupBean groupBean = restClient.group("RestAdmin");
        assertEquals("RestAdmin", groupBean.getName());
        assertEquals("Rest Admin", groupBean.getDescription());
    }

    @Test
    void testUserDef() {
        UserDefBean userDefBean = restClient.userDef();
        assertEquals(6, userDefBean.getAttributeDefs().size());
        UserAttributeDefBean userAttributeDefBean = userDefBean.getAttributeDefs().get(0);
        assertEquals("name", userAttributeDefBean.getName());
        assertEquals("Username", userAttributeDefBean.getUiFormName());
        assertEquals("STRING", userAttributeDefBean.getType());
        assertEquals(true, userAttributeDefBean.isRequired());
    }

    @Test
    void testUserLocales() {
        UserLocalesBean userLocalesBean = restClient.userLocales();
        assertEquals(11, userLocalesBean.getUserLocales().size());
        UserLocale userLocale = userLocalesBean.getUserLocales().get(0);
        assertEquals("ar_SA", userLocale.getName());
        assertEquals("Arabic (Saudi Arabia)", userLocale.getDescription());
        assertEquals(2, userLocale.getOrdinal());
    }

    @Test
    void testVistoryHistory() {
        // ListKeyValuePairs vistoryHistory = restClient.vistoryHistory(1502442402863L,
        // 1502442402880L);
        // TODO Build JSK with visitor history and add user to group to allow access.
    }

    @AfterEach
    void afterEach() {
        jskContainer.stop();
    }

}
