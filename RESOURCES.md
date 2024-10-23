# Authenticated Resources

## Create an authenticated REST client

Using the builder so you can configure things like `readTimeout`, `tokenProvider` etc

```java
    AuthenticatedRestClient client = RestClient.builder("http://localhost:7003/sites")
                .authenticated("fwadmin", "xceladmin").cachingTokenProvider().build();
```

Short cut using all defaults

```java
    AuthenticatedRestClient client = RestClient.authenticated("http://localhost:7003/sites", "fwadmin", "xceladmin");
```

### /REST/types

```java
    AssetTypesBean assetTypesBean = client.types();
```

### /REST/types/{type}

```java
    AssetTypeBean assetTypeBean = client.type(Type.AVIARTICLE);
```

Create an asset type.

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

````java
    AssetBean assetBean = client.sites(Site.AVISPORTS).types(Type.AVIARTICLE).asset(1328196047241L);
    client.sites(Site.AVISPORTS).types(Type.AVIARTICLE).assets(1328196047241L).post(assetBean);
```

**DELETE**

```java
    client.sites(Site.AVISPORTS).types(Type.AVIARTICLE).assets(1328196047241L).delete();
```

## Create a v1 aggregated REST client

Using the builder so you can configure the client with `readTimeout`, `connectTimeout`, `trace` etc

```java
    V1RestClient client = RestClient.builder("http://localhost:7003/sites").trace().readTimeout(Duration.ofSeconds(5)).build();

````

Short cut using all defaults

```java
    V1RestClient client = RestClient.v1("http://localhost:7003/sites");

```

## Exceptions

### Request Exceptions

### Response Exceptions
