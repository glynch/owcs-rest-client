# Oracle WebCenter Sites Rest Client

[Rest API](https://docs.oracle.com/middleware/12213/related-docs/WBCSR/wemrestresources.htm#i1019996)

[Aggregated REST API](https://docs.oracle.com/en/middleware/webcenter/sites/12.2.1.3/wcsrt/index.html)

A fluent rest client that supports legacy authenticated REST resources, as well as the newer v1 aggregated REST resources.

See [Examples](https://github.com/glynch/owcs-rest-client/blob/main/RESOURCES.md)

## Running locally

You will need to install `sites-rest-api.jar` and into your maven respository.

For example

```bash
    mvn install:install-file -Dfile=<JSK_HOME>/JSK-12.2.1.3.0-2016-03-31/apache-tomcat-7.0.65-sites/webapps/sites/WEB-INF/lib/sites-rest-api.jar -DgroupId=com.oracle.wcsites -DartifactId=sites-rest-api -Dversion=12.2.1.3.0 -Dpackaging=jar
```
