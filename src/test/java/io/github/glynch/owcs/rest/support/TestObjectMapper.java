package io.github.glynch.owcs.rest.support;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatwire.rest.beans.SitesBean;

public class TestObjectMapper {

    static ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() {
        objectMapper = new DefaultObjectMapper();
        ;
    }

    @Test
    void testSitesBeanMixin() throws JsonParseException, JsonMappingException, IOException {
        SitesBean sitesBean = objectMapper.readValue(new File("src/test/resources/sites.json"), SitesBean.class);
        assertEquals(4, sitesBean.getTotal().intValue());
    }

}
