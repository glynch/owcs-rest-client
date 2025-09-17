# Oracle WebCenter Sites Rest Client

This is a work in progress and can change frequently.

[Rest API](https://docs.oracle.com/middleware/12213/related-docs/WBCSR/wemrestresources.htm#i1019996)

[Aggregated REST API](https://docs.oracle.com/en/middleware/webcenter/sites/12.2.1.3/wcsrt/index.html)

A fluent Java rest client that supports legacy authenticated REST resources, as well as the newer v1 aggregated REST resources.


## Authenticated REST Resources

### Create an `AuthenticatedRestClient` client

```bash
    AuthenticatedRestClient client = AuthenticatedRestClient
                .builder("http://localhost:7003/sites", "fwadmin",
                        "xceladmin")
                .cachingTokenProvider().build();
```

### AssetType



## Aggregated REST Resources 

