package io.github.glynch.owcs.rest.it;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.fatwire.rest.beans.AclsBean;
import com.fatwire.rest.beans.DeviceBean;
import com.fatwire.rest.beans.DeviceGroupBean;
import com.fatwire.rest.beans.TimezoneBean;

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

    @AfterEach
    void afterEach() {
        jskContainer.stop();
    }

}
