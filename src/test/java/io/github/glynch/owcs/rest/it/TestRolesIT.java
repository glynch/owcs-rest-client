package io.github.glynch.owcs.rest.it;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.fatwire.rest.beans.Role;
import com.fatwire.rest.beans.RoleBean;
import com.fatwire.rest.beans.RolesBean;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient;
import io.github.glynch.owcs.test.containers.JSKContainer;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestRolesIT {

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
        RoleBean roleBean = restClient.role("AdvancedUser").read();
        assertEquals("AdvancedUser", roleBean.getName());
        assertEquals("Advanced User", roleBean.getDescription());
    }

    @Test
    void testUpdateRole() {
        RoleBean roleBean = restClient.role("AdvancedUser").read();
        roleBean.setDescription("Updated description");
        RoleBean updatedRoleBean = restClient.role("AdvancedUser").update(roleBean);
        assertEquals("Updated description", updatedRoleBean.getDescription());
    }

    @Test
    void testCreateRole() {
        RoleBean roleBean = new RoleBean();
        roleBean.setName("TestRole");
        roleBean.setDescription("Test Role description");
        RoleBean createdRoleBean = restClient.role("TestRole").create(roleBean);
        assertEquals("TestRole", createdRoleBean.getName());
        assertEquals("Test Role description", createdRoleBean.getDescription());
    }

    @AfterEach
    void afterEach() {
        jskContainer.stop();
    }

}
