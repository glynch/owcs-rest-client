package io.github.glynch.owcs.rest.bean.builder;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import io.github.glynch.owcs.rest.bean.builders.AssetBeanBuilder;
import io.github.glynch.owcs.rest.bean.builders.Builders;
import io.github.glynch.owcs.rest.bean.utils.AssetIds;
import io.github.glynch.owcs.rest.bean.utils.BeanUtils;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestAssetBeanBuilder {

    @Test
    void testAssetBeanBuilder() {
        AssetBeanBuilder assetBeanBuilder = Builders.assetBeanBuilder("AVIArticle", "Article", "Test", "avisports");
        assetBeanBuilder
                .attribute("body", "This is the body")
                .attribute("postDate", new Date())
                .parents("Category", AssetIds.of("ArticleCategory", 1327351719208L));

        System.out.println(BeanUtils.toString(assetBeanBuilder.build()));
    }

}
