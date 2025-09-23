package io.github.glynch.owcs.rest.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Webreference;

import io.github.glynch.owcs.rest.bean.utils.AssetIds;
import io.github.glynch.owcs.rest.support.DefaultObjectMapper;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestAssetBeanFacade {

    private AssetBean assetBean;
    private ObjectMapper objectMapper = new DefaultObjectMapper();
    private AssetBeanFacade assetBeanFacade;

    @BeforeEach
    void beforeEach() throws JsonParseException, JsonMappingException, IOException {
        File file = new File("src/test/resources/assets/AVIArticle-1328196047309.json");
        assetBean = objectMapper.readValue(file, AssetBean.class);
        assetBeanFacade = new AssetBeanFacade(assetBean);
    }

    @Test
    void testAssetBeanFacade() {
        assertEquals("AVIArticle:1328196047309", assetBeanFacade.getId());
        assertEquals(AssetIds.of("AVIArticle", 1328196047309L), assetBeanFacade.getAssetId());
        assertEquals("AVIArticle", assetBeanFacade.getAssetId().getType());
        assertEquals(1328196047309L, assetBeanFacade.getAssetId().getId());
        assertEquals("Rookie Skier Makes Her Mark ", assetBeanFacade.getName());
        assertEquals("", assetBeanFacade.getDescription());
        assertEquals("Article", assetBeanFacade.getSubtype());
        assertEquals(Collections.singletonList("avisports"), assetBeanFacade.getSites());
        assertEquals("ArticleLayout", assetBeanFacade.getTemplate());
        assertEquals("fwadmin", assetBeanFacade.getCreatedBy());
        assertEquals("fwadmin", assetBeanFacade.getUpdatedBy());
        assertEquals("e0df548b-3acd-466e-911b-d29e97535643", assetBeanFacade.getFwUid());
        System.out.println(assetBeanFacade.getStringAttribute("headline"));
        List<Webreference> webReferences = assetBeanFacade.getWebReferences();
        Webreference webReference = webReferences.get(0);
        assertEquals("http://localhost:7003/sites/avi/article/rookie_skier_makes_her_mark_.html",
                webReference.getHref());
        assertEquals("avisports/AVIArticle/ArticleLayout", webReference.getTemplate());
        assertEquals("Avi", webReference.getWebroot());
        assertEquals(200, webReference.getHttpstatus());
        assertEquals("article/rookie_skier_makes_her_mark_.html", webReference.getUrl());

    }

}
