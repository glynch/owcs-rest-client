# Oracle WebCenter Sites Rest Client

This is a work in progress and can change frequently.

[Rest API](https://docs.oracle.com/middleware/12213/related-docs/WBCSR/wemrestresources.htm#i1019996)

[Aggregated REST API](https://docs.oracle.com/en/middleware/webcenter/sites/12.2.1.3/wcsrt/index.html)

A fluent Java rest client that supports legacy authenticated REST resources, as well as the newer v1 aggregated REST resources.


## Authenticated REST Resources

### Create an `AuthenticatedRestClient` client

```java
    AuthenticatedRestClient restClient = AuthenticatedRestClient
                .builder("http://localhost:7003/sites", "fwadmin",
                        "xceladmin")
                .cachingTokenProvider().build();
```


### /types

```java
    AssetTypesBean assetTypesBean = restClient.types();
```

### /types/{type}


```java
    AssetTypeBean type = restClient.type("AVIArticle").read()
```

```java
    AssetTypeBeanBuilder builder = Builders.assetTypeBeanBuilder("AVITest", "Test")
                .canBeChild(false)
                .description("AVITest description")
                .singleValuedAttribute("body", "Body", AttributeTypeEnum.STRING, false, 2000); 
    type = restClient.type("AVITest").create(builder.build());
```

```java
    restClient.type("AVIArticle").delete();
```

### /types/{type}/subtypes

```java
    AssetTypesBean subtypes = restClient.type("AVIArticle").subtypes();
```

### /types/{type}/subtypes/{subtype}

```java
    AssetTypeBean subtype = restClient.type("AVIArticle").subtype("Article");
```

### /roles

```java
    RolesBean rolesBean = restClient.roles();
```

### /roles{role}

```java
    RoleBean roleBean = restClient.role("AdvancedUser").read();
```

```java
    RoleBean roleBean = new RoleBean();
    roleBean.setName("TestRole");
    roleBean.setDescription("Test Role description");
    RoleBean createdRoleBean = restClient.role("TestRole").create(roleBean);
```

```java
    restClient.role("TestRole").delete();
```

```java
    RoleBean roleBean = restClient.role("AdvancedUser").read();
    roleBean.setDescription("Updated description");
    RoleBean updatedRoleBean = restClient.role("AdvancedUser").update(roleBean);
```

### /timezone

```java
    TimezoneBean timezoneBean = restClient.timezone();
```

### /currentdevice

```java
    DeviceBean deviceBean = restClient.currentDevice();
```

### /acls

```java
    AclsBean aclsBean = restClient.acls();
```

### /groups

```java
    GroupsBean groupsBean = restClient.groups();
```

### /groups/{group}

```java
    GroupBean groupBean = restClient.group("RestAdmin");
```

### /userdef

```java
    UserDefBean userDefBean = restClient.userDef();
```

### /userlocales

```java
    UserLocalesBean userLocalesBean = restClient.userLocales();
```

### /users

```java
    UsersBean usersBean = restClient.users();
```

### /users/{user}

```java
    UserBean userBean = restClient.user("Bill").read();
```

```java
    UserBean userBean = new UserBean();
    userBean.setName("TestUser");
    userBean.setPassword("TestUser");
    userBean.getAcls().addAll(Arrays.asList("Browser", "ElementReader"));
    UserBean createdUserBean = restClient.user("TestUser").create(userBean);
```

```java
    UserBean userBean = restClient.user("Bill").read();
    userBean.setEmail("bill@test.com");
    userBean.getAcls().add("xceladmin");
    UserBean updatedUserBean = restClient.user("Bill").update(userBean);
```

```java
    restClient.user("Bill").delete();
```

### /applications

```java
    ApplicationsBean applicationsBean = restClient.applications();
```

### /applications/{applicationid}

```java
    ApplicationBean applicationBean = restClient.application(1262707329030L).read();
```

```java
    ApplicationBeanBuilder builder = Builders.applicationBeanBuilder("TestApplication",
                                FW_WEM_FRAMEWORK_LAYOUT_RENDERER);
    builder.description("Test Application Description")
        .shortDescription("Test Application Short description")
        .iconUrl("wemresources/images/icons/apps/Admin.png")
        .activeIconUrl("wemresources/images/icons/apps/AdminActive.png")
        .clickIconUrl("wemresources/images/icons/apps/AdminClick.png")
        .hoverIconUrl("wemresources/images/icons/apps/Admin.png")
        .tooltip("Test Application Tooltip")
        .site("avisports", "SitesUser");

    ViewBuilder viewBuilder = Builders.viewBuilder("TestApplication", FW_WEM_FRAMEWORK_IFRAME_RENDERER)
        .description("Test Application");

    ApplicationBean applicationBean = builder.view(viewBuilder.build()).build();
    ApplicationBean testApplicationBean = restClient.application(0L).create(applicationBean);
```

```java
    ApplicationBean applicationBean = restClient.application(1262707329030L).read();
    applicationBean.setDescription("Updated Description");
    applicationBean.setShortdescription("Updated ShortDescription");
    applicationBean.setTooltip("Updated Tooltip");
    ApplicationBean updatedApplicationBean = restClient.application(applicationBean.getId())
        .update(applicationBean);
```

```java
    restClient.application(1262707329030L).delete();
```

### /indexes

```java
    IndexConfigsBean indexConfigsBean = restClient.indexes();
```

### /indexes/{index}

```java
    IndexConfigBean indexConfigBean = restClient.index("ContentCloud").read();
```

### /sites

```java
    SitesBean sitesBean = restClient.sites();
```

### /sites/{site}

```java
    SiteBean siteBean = restClient.site("avisports").read();
```

```java
    SiteBeanBuilder siteBeanBuilder = Builders.siteBeanBuilder("testsite", "testsite description");
    siteBeanBuilder.type("AVIArticle").type("Page").user("Bill", "SitesUser", "Writer");
    SiteBean siteBean = restClient.site("testsite").create(siteBeanBuilder.build());
```

```java
    SiteBean siteBean = restClient.site("avisports").read();
    siteBean.setDescription(siteBean.getDescription() + " UPDATED");
    SiteBean updatedSiteBean = restClient.site("avisports").update(siteBean);
```

```java
    restClient.site("avisports").delete();
```

## Aggregated REST Resources 

