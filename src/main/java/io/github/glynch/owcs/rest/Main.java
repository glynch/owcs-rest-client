package io.github.glynch.owcs.rest;

import io.github.glynch.owcs.rest.client.RestClient;
import io.github.glynch.owcs.rest.client.authenticated.AuthenticatedRestClient;
import io.github.glynch.owcs.rest.client.v1.V1RestClient;

public class Main {

    public static void main(String[] args) {

        AuthenticatedRestClient client2 = RestClient.builder("http://localhost:7003/sites")
                .authenticated("fwadmin", "xceladmin").cachingTokenProvider().build();

        // AuthenticatedRestClient client =
        // RestClient.authenticated("http://localhost:7003/sites", "fwadmin",
        // "xceladmin");
        AuthenticatedRestClient client = RestClient.authenticated("http://localhost:7003/sites", "fwadmin",
                "xceladmin");

        V1RestClient v1Client = RestClient.builder("http://localhost:7003/sites").build();

        System.out.println(client.types().getTypes().size());
        System.out.println(client.applications().getApplications().size());
        System.out.println(client.sites().getSites().size());
        System.out.println(client.timeZone().getDisplayName());
        System.out.println(client.userDef().getAttributeDefs().size());
        System.out.println(client.userLocales().getUserLocales().size());
        System.out.println(client.roles().getRoles().size());
        System.out.println(client.users().getUsers().size());
        System.out.println(client.groups().getGroups().size());
        System.out.println(client.group("RestAdmin").getDescription());
        System.out.println(client.indexes().getIndexConfigs().size());
        System.out.println(client.types("AVIArticle").get().getName());
        //
        System.out.println(client.types("AVIArticle").subtypes().getTypes().size());
        //
        System.out.println(client.types("AVIArticle").subtypes("Article").getSubtype());
        // // System.out.println(client.currentDevice().getDeviceCapabilitiesBean());

        // AssetTypeBean assetTypeBean = client.types("AVIArticle").get();
        // AssetTypeBean b = client.types("AVIArticle").put(assetTypeBean);
        // System.out.println(b.getName());
        // HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        // loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // builder.addInterceptor(loggingInterceptor);

        // TokenProvider tokenProvider = TokenProvider.create();

        // OkHttpClient client = builder.build();
        // ResponseErrorHandler errorHandler = new
        // DefaultResponseErrorHandler(objectMapper);
        // RestApi restApi = new DefaultRestApi(client, objectMapper, errorHandler);
        // AuthenticatedResponseErrorHandler authenticatedErrorHandler = new
        // AuthenticatedResponseErrorHandler(
        // objectMapper);
        // AuthenticatedRestApi authenticatedRestApi = new
        // DefaultAuthenticatedRestApi(client, objectMapper,
        // tokenProvider, authenticatedErrorHandler, "http://localhost:7003/sites",
        // "fwadmin", "xceladmin");
        // try {
        // authenticatedRestApi.get(
        // "http://localhost:7003/sites/REST/sites/avisports/types/AVIArticle/assets/1330880425951",
        // AssetBean.class);
        // } catch (RestClientResponseException e) {
        // System.out.println(e.getMessage());
        // } catch (RestClientRequestException e) {
        // System.out.println(e.getMessage());
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

    }
}
