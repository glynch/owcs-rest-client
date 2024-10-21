package io.github.glynch.owcs.rest;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
import com.fatwire.rest.beans.Parent;
import com.fatwire.rest.beans.RolesBean;
import com.fatwire.rest.beans.SitesBean;
import com.fatwire.rest.beans.Struct;
import com.fatwire.rest.beans.UserLocalesBean;

import io.github.glynch.owcs.rest.client.api.AuthenticatedRestApi;
import io.github.glynch.owcs.rest.client.api.DefaultAuthenticatedRestApi;
import io.github.glynch.owcs.rest.client.api.DefaultRestApi;
import io.github.glynch.owcs.rest.client.api.RestApi;
import io.github.glynch.owcs.rest.client.authenticated.support.AuthenticatedResponseErrorHandler;
import io.github.glynch.owcs.rest.client.exceptions.RestClientRequestException;
import io.github.glynch.owcs.rest.client.exceptions.RestClientResponseException;
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
import io.github.glynch.owcs.rest.client.sso.TokenProvider;
import io.github.glynch.owcs.rest.client.support.ResponseErrorHandler;
import io.github.glynch.owcs.rest.client.v1.support.DefaultResponseErrorHandler;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class Main {

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
    }
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final JavaTimeModule javaTimeModule = new JavaTimeModule();

    static {
        objectMapper.registerModule(javaTimeModule);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.setMixIns(mixins);
    }

    public static void main(String[] args) {

        // AuthenticatedRestClient client =
        // RestClient.builder("http://localhost:7003/sites").trace()
        // .authenticated("fwadmin", "xceladmin").cachingTokenProvider().build();

        // // AuthenticatedRestClient client =
        // // RestClient.authenticated("http://localhost:7003/sites", "fwadmin",
        // // "xceladmin");

        // V1RestClient v1Client = RestClient.v1("http://localhost:7003/sites");

        // // System.out.println(client.types().getTypes().size());
        // // System.out.println(client.applications().getApplications().size());
        // // System.out.println(client.sites().getSites().size());
        // // System.out.println(client.timeZone().getDisplayName());
        // // System.out.println(client.userDef().getAttributeDefs().size());
        // // System.out.println(client.userLocales().getUserLocales().size());
        // // System.out.println(client.roles().getRoles().size());
        // // System.out.println(client.users().getUsers().size());
        // // System.out.println(client.groups().getGroups().size());
        // // System.out.println(client.group("RestAdmin").getDescription());
        // // System.out.println(client.indexes().getIndexConfigs().size());
        // // System.out.println(client.types("AVIArticle").get().getName());
        // //
        // System.out.println(client.types("AVIArticle").subtypes().getTypes().size());
        // //
        // System.out.println(client.types("AVIArticle").subtypes("Article").getSubtype());
        // // System.out.println(client.currentDevice().getDeviceCapabilitiesBean());

        // AssetTypeBean assetTypeBean = client.types("AVIArticle").get();
        // AssetTypeBean b = client.types("AVIArticle").put(assetTypeBean);
        // System.out.println(b.getName());
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(loggingInterceptor);

        TokenProvider tokenProvider = TokenProvider.create();

        OkHttpClient client = builder.build();
        ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler(objectMapper);
        RestApi restApi = new DefaultRestApi(client, objectMapper, errorHandler);
        AuthenticatedResponseErrorHandler authenticatedErrorHandler = new AuthenticatedResponseErrorHandler(
                objectMapper);
        AuthenticatedRestApi authenticatedRestApi = new DefaultAuthenticatedRestApi(client, objectMapper,
                tokenProvider, authenticatedErrorHandler, "http://localhost:7003/sites",
                "fwadmin", "xceladmin");
        try {
            authenticatedRestApi.get(
                    "http://localhost:7003/sites/REST/sites/avisports/types/AVIArticle/assets/1330880425951",
                    AssetBean.class);
        } catch (RestClientResponseException e) {
            System.out.println(e.getMessage());
        } catch (RestClientRequestException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
