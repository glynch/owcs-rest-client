package io.github.glynch.owcs.rest.support;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.fatwire.rest.beans.ApplicationBean;
import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssetInfo;
import com.fatwire.rest.beans.AssetTypesBean;
import com.fatwire.rest.beans.AssetsBean;
import com.fatwire.rest.beans.Association;
import com.fatwire.rest.beans.Associations;
import com.fatwire.rest.beans.AssociationsBean;
import com.fatwire.rest.beans.Attribute;
import com.fatwire.rest.beans.EnabledTypesBean;
import com.fatwire.rest.beans.IndexConfigsBean;
import com.fatwire.rest.beans.LayoutTypeEnum;
import com.fatwire.rest.beans.Parent;
import com.fatwire.rest.beans.RolesBean;
import com.fatwire.rest.beans.SitesBean;
import com.fatwire.rest.beans.Struct;
import com.fatwire.rest.beans.UserLocalesBean;
import com.fatwire.rest.beans.UsersBean;
import com.fatwire.rest.beans.ViewTypeEnum;

import io.github.glynch.owcs.rest.support.deserializers.LayoutTypeEnumDeserializer;
import io.github.glynch.owcs.rest.support.deserializers.ViewTypeEnumDeserializer;
import io.github.glynch.owcs.rest.support.mixins.ApplicationBeanMixin;
import io.github.glynch.owcs.rest.support.mixins.AssetBeanMixin;
import io.github.glynch.owcs.rest.support.mixins.AssetInfoMixin;
import io.github.glynch.owcs.rest.support.mixins.AssetTypesBeanMixin;
import io.github.glynch.owcs.rest.support.mixins.AssetsBeanMixin;
import io.github.glynch.owcs.rest.support.mixins.AssociationMixin;
import io.github.glynch.owcs.rest.support.mixins.AssociationsBeanMixin;
import io.github.glynch.owcs.rest.support.mixins.AssociationsMixin;
import io.github.glynch.owcs.rest.support.mixins.AttributeDataMixin;
import io.github.glynch.owcs.rest.support.mixins.EnabledTypesBeanMixin;
import io.github.glynch.owcs.rest.support.mixins.IndexConfigsBeanMixin;
import io.github.glynch.owcs.rest.support.mixins.ListMixin;
import io.github.glynch.owcs.rest.support.mixins.ParentMixin;
import io.github.glynch.owcs.rest.support.mixins.RolesBeanMixin;
import io.github.glynch.owcs.rest.support.mixins.SitesBeanMixin;
import io.github.glynch.owcs.rest.support.mixins.StructMixin;
import io.github.glynch.owcs.rest.support.mixins.UserLocalesBeanMixin;
import io.github.glynch.owcs.rest.support.mixins.UsersBeanMixin;

public final class DefaultObjectMapper extends ObjectMapper {

    private static final Map<Class<?>, Class<?>> mixins = new HashMap<>();
    private static final JavaTimeModule javaTimeModule = new JavaTimeModule();
    private static final SimpleModule simpleModule = new SimpleModule();
    private static final JaxbAnnotationModule jaxbAnnotationModule = new JaxbAnnotationModule();
    static {
        mixins.put(AssetBean.class, AssetBeanMixin.class);
        mixins.put(Attribute.Data.class, AttributeDataMixin.class);
        mixins.put(Associations.class, AssociationsMixin.class);
        mixins.put(Association.class, AssociationMixin.class);
        mixins.put(AssetsBean.class, AssetsBeanMixin.class);
        mixins.put(com.fatwire.rest.beans.List.class, ListMixin.class);
        mixins.put(Struct.class, StructMixin.class);
        mixins.put(EnabledTypesBean.class, EnabledTypesBeanMixin.class);
        mixins.put(Parent.class, ParentMixin.class);
        mixins.put(AssetInfo.class, AssetInfoMixin.class);
        mixins.put(AssetTypesBean.class, AssetTypesBeanMixin.class);
        mixins.put(SitesBean.class, SitesBeanMixin.class);
        mixins.put(AssociationsBean.class, AssociationsBeanMixin.class);
        mixins.put(UserLocalesBean.class, UserLocalesBeanMixin.class);
        mixins.put(RolesBean.class, RolesBeanMixin.class);
        mixins.put(IndexConfigsBean.class, IndexConfigsBeanMixin.class);
        mixins.put(UsersBean.class, UsersBeanMixin.class);
        mixins.put(ApplicationBean.class, ApplicationBeanMixin.class);
        simpleModule.addDeserializer(LayoutTypeEnum.class, new LayoutTypeEnumDeserializer());
        simpleModule.addDeserializer(ViewTypeEnum.class, new ViewTypeEnumDeserializer());
    }

    public DefaultObjectMapper() {
        this(null);
    }

    public DefaultObjectMapper(JsonFactory factory) {
        super(factory);
        registerModule(simpleModule);
        registerModule(javaTimeModule);
        registerModule(jaxbAnnotationModule);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        configure(SerializationFeature.INDENT_OUTPUT, true);
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        setMixIns(mixins);
    }

}
