# Oracle WebCenter Sites Rest Client

This is a work in progress and can change frequently.

[Rest API](https://docs.oracle.com/middleware/12213/related-docs/WBCSR/wemrestresources.htm#i1019996)

[Aggregated REST API](https://docs.oracle.com/en/middleware/webcenter/sites/12.2.1.3/wcsrt/index.html)

A fluent rest client that supports legacy authenticated REST resources, as well as the newer v1 aggregated REST resources.

See [Authenticated Examples](https://github.com/glynch/owcs-rest-client/blob/main/RESOURCES.md)

See [V1 Examples](https://github.com/glynch/owcs-rest-client/blob/main/V1RESOURCES.md)

## Build

To build this project you will need to install `sites-rest-api.jar, sites-app.jar` and `sites-asset-api.jar` into your maven respository.

You can use the `install.sh` script

Pass in the location of your JSK_HOME

```bash
    ./install.sh /Users/glynch/development/owcs/jsk/WCS-JSK-12.2.1.3.0
```
