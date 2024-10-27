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
    AssetTypeBean assetTypeBean = client.type(Type.AVIARTICLE);
```

**CREATE**

```java
    AssetTypeBean assetTypeBean = ....
    assetTypeBean = client.types(TYPE.AVIARTICLE).put(assetTypeBean);
```

### /REST/types/{type}/subtypes

```java
    AssetTypesBean assetTypesBean = client.types(Type.AVIARTICLE).subtypes();
```

### /REST/types/{type}/subtypes/{subtype}

```java
    AssetTypeBean assetTypeBean = client.types(Type.AVIARTICLE).subtype(Subtype.Article);
```

### /REST/sites/{site}/types

```java
    EnabledTypesBean enabledTypesBean = client.sites(Site.AVISPORTS).types();
```

## Asset

### /REST/sites/{site}/types/{type}/assets/{id}

**READ**

```java
    AssetBean assetBean = client.sites(Site.AVISPORTS).types(Type.AVIARTICLE).asset(1328196047241L);
```

**CREATE**

```java
    AssetBean assetBean = ...
    client.sites(Site.AVISPORTS).types(Type.AVIARTICLE).assets(0).put(assetBean);
```

**UPDATE**

```java
    AssetBean assetBean = client.sites(Site.AVISPORTS).types(Type.AVIARTICLE).asset(1328196047241L);
    client.sites(Site.AVISPORTS).types(Type.AVIARTICLE).assets(1328196047241L).post(assetBean);
```

**DELETE**

```java
    client.sites(Site.AVISPORTS).types(Type.AVIARTICLE).assets(1328196047241L).delete();
```

### /REST/sites/{site}/types/{type}/assets/{id}/associations

```java
    AssociationsBean associationsBean = client.sites(Site.AVISPORTS).types(Type.AVIARTICLE)
                                .assets(1328196047241L).associations();
```

### /REST/sites/{site}/types/{type}/assets/{id}/associations/{association}

```java
     AssociationBean associationBean = client.sites(Site.AVISPORTS).types(Type.AVIARTICLE)
                                .assets(1328196047241L).association("association");
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

```java
   SiteBean siteBean = client.site(Site.AVISPORTS).get();
```

```java
    SiteBean siteBean = ...
    siteBean = client.site(Site.AVISPORTS).put(siteBean);
```

```java
    SiteBean siteBean = ...
    siteBean = client.site(Site.AVISPORTS).post(siteBean);
```

```java
    client.site(Site.AVISPORTS).delete();
```

## Site Plan Navigation

### /REST/sites/{site}/navigation

```java
    NavigationBean navigationBean = client.sites(Site.AVISPORTS).navigation();
```

### /REST/sites/{sitename}/navigation/{id}

```java
    NavigationBean navigationBean = client.sites(Site.AVISPORTS).navigation(1322052581735L);
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

**GET**

```java
     ApplicationBean applicationBean = client.application(1253211458856L);
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
    SiteUserBean siteUserBean = client.sites(Site.AVISPORTS).users();
```

## Exceptions

### Request Exceptions

### Response Exceptions
