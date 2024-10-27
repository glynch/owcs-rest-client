# V1 Aggregated Resources

## Create a `V1RestClient` client

Using the builder so you can configure the client with `readTimeout`, `connectTimeout`, `trace` etc

```java
    V1RestClient client = RestClient.builder("http://localhost:7003/sites").trace().readTimeout(Duration.ofSeconds(5)).build();

```

Short cut using all defaults

```java
    V1RestClient client = RestClient.v1("http://localhost:7003/sites");

```
