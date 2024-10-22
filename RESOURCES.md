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

### Types

#### /REST/types

```java
    AssetTypesBean assetTypesBean = client.types();
```

#### /REST/types/{type}

```java
    AssetTypeBean assetTypeBean = client.types(Type.AVIARTICLE).get();
```

Create an asset type.

```java
    AssetTypeBean assetTypeBean = ....
    assetTypeBean = client.types(TYPE.AVIARTICLE).put(assetTypeBean);
```

## Create a v1 aggregated REST client

Using the builder so you can configure the client with `readTimeout`, `connectTimeout`, `trace` etc

```java
    V1RestClient client = RestClient.builder("http://localhost:7003/sites").trace().readTimeout(Duration.ofSeconds(5)).build();

```

Short cut using all defaults

```java
    V1RestClient client = RestClient.v1("http://localhost:7003/sites");

```

## Exceptions

### Request Exceptions

### Response Exceptions
