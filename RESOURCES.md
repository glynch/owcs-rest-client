# Authenticated Resources

## Create an `AuthenticatedRestClient` client

Using the builder so you can configure things like `readTimeout`, `tokenProvider` etc

```java
    AuthenticatedRestClient client = RestClient.builder("http://localhost:7003/sites")
                .authenticated("fwadmin", "xceladmin").cachingTokenProvider().build();
```

Short cut using all defaults

```java
    AuthenticatedRestClient client = RestClient.authenticated("http://localhost:7003/sites", "fwadmin", "xceladmin");
```

## AssetType

### /REST/types

```java
    AssetTypesBean assetTypesBean = client.types();
```

### /REST/types/{type}

**READ**

```java
    AssetTypeBean assetTypeBean = client.type(Type.AVIARTICLE).get();
```

**CREATE**

```java
    AssetTypeBeanBuilder assetTypeBeanBuilder = AssetTypeBeanBuilder.builder("AVIArticle2", "Article2")
                .description("Article2")
                .singleValuedAttribute("headline2", "Headline", AttributeTypeEnum.STRING, true, 256);

        client.put(assetTypeBeanBuilder.build());
```

### /REST/types/{type}/subtypes

```java
    AssetTypesBean assetTypesBean = client.type(Type.AVIARTICLE).subtypes();
```

### /REST/types/{type}/subtypes/{subtype}

```java
    AssetTypesBean assetTypeBeans = client.type(Type.AVIARTICLE).subtype(Subtype.Article);
```

### /REST/sites/{site}/types

```java
    EnabledTypesBean enabledTypesBean = client.site(Site.AVISPORTS).types();
```

## Asset

### /REST/sites/{site}/types/{type}/assets/{id}

**READ**

```java
    AssetBean assetBean = client.site(Site.AVISPORTS).type(Type.AVIARTICLE).id(1328196047241L).get();
```

**CREATE**

```java
    AssetBeanBuilder builder = AssetBeanBuilder.builder("Page", "AVISection",
                "This is a test Page")
                .template("SectionLayoutGreen")
                .sites("avisports")
                .startDate(new Date())
                .endDate(new Date())
                .association("contentList1", AssetIds.of("AVIArticle:1328196049021"),
                        AssetIds.of("AVIArticle:1328196048989"));
        AssetBean assetBean = builder.build();
    client.site(Site.AVISPORTS).put(assetBean);
```

**UPDATE**

```java
    AssetBean assetBean = client.site(Site.AVISPORTS).type(Type.AVIARTICLE).id(1328196047241L).get();
    ....
    client.site(Site.AVISPORTS).post(assetBean);
```

**DELETE**

```java
    client.site(Site.AVISPORTS).type(Type.AVIARTICLE).id(1328196047241L).delete();
```

### /REST/sites/{site}/types/{type}/assets/{id}/associations

```java
    AssociationsBean associationsBean = client.site(Site.AVISPORTS).type(Type.AVIARTICLE)
                                .id(1328196047241L).associations();
```

### /REST/sites/{site}/types/{type}/assets/{id}/associations/{association}

```java
     AssociationBean associationBean = client.site(Site.AVISPORTS).type(Type.AVIARTICLE)
                                .id(1328196047241L).association(Association.CONTENT_LIST1);
```

## Index

### /REST/indexes

```java
     IndexConfigsBean indexConfigsBean = client.indexes();
```

### /REST/indexes/{index}

```java
     IndexConfigBean indexConfigBean = client.index(Index.GLOBAL);
```

## Site Resoures

### /REST/sites

```java
    SitesBean sitesBean = client.sites();
```

### /REST/sites/{site}

**Read**

```java
   SiteBean siteBean = client.site(Site.AVISPORTS).get();
```

**CREATE**

```java
    SiteBeanBuilder siteBeanBuilder = SiteBeanBuilder.builder("testsite")
                .description("This is the test site")
                .types("Page", "AVIArticle")
                .user("fwadmin", "AdvancedUser", "SitesUser", "SiteAdmin", "GeneralAdmin")
                .user("Bill", "AdvancedUser", "Writer", "Reviewer");

        client.put(siteBeanBuilder.build());
```

**UPDATE**

```java
    SiteBean siteBean = ...
    siteBean = client.post(siteBean);
```

**DELETE**

```java
    client.site(Site.AVISPORTS).delete();
```

## Site Plan Navigation

### /REST/sites/{site}/navigation

```java
    NavigationBean navigationBean = client.site(Site.AVISPORTS).navigation();
```

### /REST/sites/{sitename}/navigation/{id}

```java
    NavigationBean navigationBean = client.site(Site.AVISPORTS).navigation(1322052581735L);
```

## Role

### /REST/roles

```java
    RolesBean rolesBean = client.roles();
```

### /REST/roles/{role}

```java
    RoleBean roleBean = client.role(Role.ADVANCED_USER);
```

## Application

### /REST/applications

```java
    ApplicationsBean applicationsBean = client.applications();
```

### /REST/applications/{id}

**READ**

```java
     ApplicationBean applicationBean = client.application(1253211458856L).get();
```

**CREATE**

```java

    AssetBean viewBean = client.site(Site.ADMIN_SITE).type(Type.VIEW).id(1283176967296L).get();
    ApplicationBeanBuilder applicationBean = ApplicationBeanBuilder
                .builder("My App", LayoutTypeEnum.FW_WEM_FRAMEWORK_LAYOUT_RENDERER)
                .description("Description of my app")
                .shortDescription("Short description of my app")
                .tooltip("This is a tooltip")
                .site("AdminSite", "SitesUser")
                .layoutUrl("wemresources/layout/admin.html")
                .iconUrl("wemresources/images/icons/apps/Contributor.png")
                .hoverIconUrl("wemresources/images/icons/apps/Contributor.png")
                .activeIconUrl("wemresources/images/icons/apps/ContributorClick.png")
                .systemApplication()
                .views(new ViewAdapter(viewBean))
                .build();
    applicationBean = client.put(applicationBean);
```

**UPDATE**

```java
    ApplicationBean applicationBean = client.application(1253211458856L).get();
    ...
    applicationBean = applicationBean = client.post(applicationBean);
```

**DELETE**

```java
    client.application(1253211458856L).delete();;
```

## User

### /REST/users (All Users)

```java
    UsersBean usersBean = client.users();
```

### /REST/users/{user} (Selected User)

```java
     UserBean userBean = client.user(User.FWADMIN);
```

### /REST/sites/{site}/users (All Site Users)

```java
    SiteUserBean siteUserBean = client.site(Site.AVISPORTS).users();
```

## Exceptions

### Request Exceptions

### Response Exceptions
