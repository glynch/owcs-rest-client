package io.github.glynch.owcs.rest.client;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

import io.github.glynch.owcs.rest.client.RestClient.Builder;
import io.github.glynch.owcs.rest.client.api.DefaultRestApi;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient;
import io.github.glynch.owcs.rest.client.authenticated.DefaultAuthenticatedRestClientBuilder;
import io.github.glynch.owcs.rest.client.mixins.AssetBeanMixin;
import io.github.glynch.owcs.rest.client.mixins.AssetInfoMixin;
import io.github.glynch.owcs.rest.client.mixins.AssetTypesBeanMixin;
import io.github.glynch.owcs.rest.client.mixins.AssetsBeanMixin;
import io.github.glynch.owcs.rest.client.mixins.AssociationMixin;
import io.github.glynch.owcs.rest.client.mixins.AssociationsBeanMixin;
import io.github.glynch.owcs.rest.client.mixins.AssociationsMixin;
import io.github.glynch.owcs.rest.client.mixins.AttributeDataMixin;
import io.github.glynch.owcs.rest.client.mixins.EnabledTypesBeanMixin;
import io.github.glynch.owcs.rest.client.mixins.IndexConfigsBeanMixin;
import io.github.glynch.owcs.rest.client.mixins.ListMixin;
import io.github.glynch.owcs.rest.client.mixins.ParentMixin;
import io.github.glynch.owcs.rest.client.mixins.RolesBeanMixin;
import io.github.glynch.owcs.rest.client.mixins.SitesBeanMixin;
import io.github.glynch.owcs.rest.client.mixins.StructMixin;
import io.github.glynch.owcs.rest.client.mixins.UserLocalesBeanMixin;
import io.github.glynch.owcs.rest.client.mixins.UsersBeanMixin;
import io.github.glynch.owcs.rest.client.v1.DefaultV1RestClient;
import io.github.glynch.owcs.rest.client.v1.V1RestClient;
import io.github.glynch.owcs.rest.client.v1.support.DefaultResponseErrorHandler;
import io.github.glynch.owcs.rest.support.LayoutTypeEnumDeserializer;
import io.github.glynch.owcs.rest.support.ResponseErrorHandler;
import io.github.glynch.owcs.rest.support.ViewTypeEnumDeserializer;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class DefaultRestClientBuilder implements RestClient.Builder {

    private static final Map<Class<?>, Class<?>> mixins = new HashMap<>();
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
    }
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final JavaTimeModule javaTimeModule = new JavaTimeModule();
    private static final SimpleModule simpleModule = new SimpleModule();

    static {
        simpleModule.addDeserializer(LayoutTypeEnum.class, new LayoutTypeEnumDeserializer());
        simpleModule.addDeserializer(ViewTypeEnum.class, new ViewTypeEnumDeserializer());
        objectMapper.registerModule(javaTimeModule);
        objectMapper.registerModule(simpleModule);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.setMixIns(mixins);
    }

    // deserializerByType(LayoutTypeEnum.class, new LayoutTypeEnumDeserializer())

    private static final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    private final String baseUrl;
    private final OkHttpClient.Builder builder = new OkHttpClient.Builder();
    boolean allowRedirects = false;

    public DefaultRestClientBuilder(String baseUrl) {
        Objects.requireNonNull(baseUrl, "baseUrl cannot be null");
        this.baseUrl = baseUrl;
    }

    @Override
    public Builder allowRedirects() {
        allowRedirects = true;
        return this;
    }

    @Override
    public Builder info() {
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        builder.addInterceptor(loggingInterceptor);
        return this;
    }

    @Override
    public Builder trace() {
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        return this;
    }

    @Override
    public Builder connectTimeout(Duration duration) {
        Objects.requireNonNull(duration, "duration cannot be null");
        builder.connectTimeout(duration);
        return this;
    }

    @Override
    public Builder readTimeout(Duration duration) {
        builder.readTimeout(duration);
        return this;
    }

    @Override
    public AuthenticatedRestClient.Builder authenticated(
            String username, String password) {
        Objects.requireNonNull(username, "username cannot be null");
        Objects.requireNonNull(password, "password cannot be null");

        return new DefaultAuthenticatedRestClientBuilder(builder.build(), objectMapper, baseUrl, username, password);
    }

    @Override
    public V1RestClient build() {
        ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler(objectMapper);
        return new DefaultV1RestClient(new DefaultRestApi(builder.build(), objectMapper, errorHandler), baseUrl);
    }

}
