package io.github.glynch.owcs.rest.bean;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Webreference;

import io.github.glynch.owcs.rest.support.DefaultObjectMapper;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestAssetBeanFacade {

    private AssetBean assetBean;
    private ObjectMapper objectMapper = new DefaultObjectMapper();
    private AssetBeanFacade facade;

    @BeforeEach
    void beforeEach() throws JsonParseException, JsonMappingException, IOException {
        File file = new File("src/test/resources/assets/AVIArticle-1328196047309.json");
        assetBean = objectMapper.readValue(file, AssetBean.class);
        facade = new AssetBeanFacade(assetBean);
    }

    @Test
    void testAssetBeanFacade() {

        System.out.println(facade.getId());
        System.out.println(facade.getAssetId().getType());
        System.out.println(facade.getAssetId().getId());
        System.out.println(facade.getName());
        System.out.println(facade.getDescription());
        System.out.println(facade.getSubtype());
        System.out.println(facade.getSites());
        System.out.println(facade.getTemplate());
        System.out.println(facade.getStartDate());
        System.out.println(facade.getCreatedBy());
        System.out.println(facade.getUpdatedBy());
        System.out.println(facade.getFwTags());
        System.out.println(facade.getFwUid());
        System.out.println(facade.getStringAttribute("body"));
        List<Webreference> webReferences = facade.getWebReferences();
        for (Webreference webreference : webReferences) {
            System.out.println(webreference.getHref());
        }
        facade.setStringAttribute("body", "mybody");
        facade.setStringAttribute("testattribute", "Test value");
        System.out.println(facade.getStringAttribute("body"));
        System.out.println(facade.getStringAttribute("testattribute"));

    }

}
