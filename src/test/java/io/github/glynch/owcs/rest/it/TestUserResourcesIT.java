package io.github.glynch.owcs.rest.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.fatwire.rest.beans.User;
import com.fatwire.rest.beans.UserBean;
import com.fatwire.rest.beans.UserSite;
import com.fatwire.rest.beans.UsersBean;

import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClientResponseException;
import io.github.glynch.owcs.test.containers.JSKContainer;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestUserResourcesIT {

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
        UserBean userBean = restClient.user("Bill").read();
        UserSite userSite = userBean.getSites().get(0);
        assertEquals("Bill", userBean.getName());
        assertEquals("userid=1502442347337,ou=People", userBean.getId());
        assertEquals(acls, userBean.getAcls());
        assertEquals("avisports", userSite.getSite());
        assertEquals(roles, userSite.getRoles());
    }

    @Test
    void testCreateUser() {
        UserBean userBean = restClient.user("Bill").read();
        userBean.setName("TestUser");
        userBean.setPassword("TestUser");
        userBean.setId("");
        UserBean testUserBean = restClient.user("TestUser").create(userBean);
        assertEquals("TestUser", testUserBean.getName());
    }

    @Test
    void testUpdateUser() {
        UserBean userBean = restClient.user("Bill").read();
        userBean.setEmail("bill@test.com");
        UserBean updatedUserBean = restClient.user("Bill").update(userBean);
        assertEquals("bill@test.com", updatedUserBean.getEmail());
    }

    @Test
    void testDeleteUser() {
        restClient.user("Bill").delete();
        AuthenticatedRestClientResponseException e = assertThrows(
                AuthenticatedRestClientResponseException.class,
                () -> restClient.user("Bill").read());
        assertEquals(404, e.getStatusCode());
        assertEquals("Not Found", e.getStatusText());
    }

    @AfterEach
    void afterEach() {
        jskContainer.stop();
    }

}
