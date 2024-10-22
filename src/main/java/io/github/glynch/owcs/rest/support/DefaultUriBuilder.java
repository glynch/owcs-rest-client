package io.github.glynch.owcs.rest.support;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

public class DefaultUriBuilder implements UriBuilder {

    private static final Pattern REPLACE_PATTERN = Pattern.compile("\\{([^/]+)\\}");
    private static final Pattern VARIABLES_PATTERN = Pattern.compile("\\{([^/]+)\\}");
    private final List<String> variables = new ArrayList<>();
    private final String uriTemplate;
    private final MultiValuedMap<String, String> queryParams = new ArrayListValuedHashMap<>();
    boolean encode = true;

    public DefaultUriBuilder(String uriTemplate) {
        Objects.requireNonNull(uriTemplate, "uriTemplate is required");
        this.uriTemplate = uriTemplate;
        var matcher = VARIABLES_PATTERN.matcher(uriTemplate);
        while (matcher.find()) {
            variables.add(matcher.group(1));
        }
    }

    @Override
    public UriBuilder encode() {
        this.encode = true;
        return this;
    }

    @Override
    public URI build(Map<String, ?> uriVariables) {
        var output = new StringBuilder();
        var matcher = REPLACE_PATTERN.matcher(uriTemplate);
        int lastIndex = 0;
        while (matcher.find()) {
            var variable = matcher.group(1);
            String value = uriVariables.get(variable).toString();
            value = encode ? encode(value) : value;
            output.append(uriTemplate, lastIndex, matcher.start()).append(value);
            lastIndex = matcher.end();
        }
        if (lastIndex < uriTemplate.length()) {
            output.append(uriTemplate, lastIndex, uriTemplate.length());
        } else {
            // Do nothing
        }

        if (!queryParams.isEmpty()) {
            output.append(queryString(queryParams));
        }
        return URI.create(output.toString());
    }

    @Override
    public URI build(Object... uriVariables) {
        var output = new StringBuilder();
        var matcher = REPLACE_PATTERN.matcher(uriTemplate);
        int lastIndex = 0;
        while (matcher.find()) {
            var variable = matcher.group(1);
            var index = variables.indexOf(variable);
            String value = uriVariables[index].toString();
            value = encode ? encode(value) : value;
            output.append(uriTemplate, lastIndex, matcher.start()).append(value);

            lastIndex = matcher.end();

        }
        if (lastIndex < uriTemplate.length()) {
            output.append(uriTemplate, lastIndex, uriTemplate.length());
        } else {
            // Do nothing
        }

        if (!queryParams.isEmpty()) {
            output.append(queryString(queryParams));
        }

        return URI.create(output.toString());
    }

    @Override
    public UriBuilder queryParam(String name, Object... values) {
        if (encode) {
            Stream.of(encodeUriVariables(values)).map(Object::toString)
                    .forEach(value -> this.queryParams.put(name, value));
        } else {
            Stream.of(values).map(Object::toString).forEach(value -> this.queryParams.put(name, value));
        }
        return this;
    }

    @Override
    public UriBuilder queryParams(MultiValuedMap<String, String> params) {
        System.out.println("params: " + params);
        if (encode) {
            params.entries().forEach(entry -> {
                String key = entry.getKey();
                String value = entry.getValue();
                this.queryParams.put(key, encode(value));
            });
        } else {
            queryParams.putAll(params);
        }
        return this;
    }

    public static Object[] encodeUriVariables(Object... uriVariables) {
        return Arrays.stream(uriVariables)
                .map(value -> {
                    String stringValue = (value != null ? value.toString() : "");
                    return encode(stringValue);
                })
                .toArray();
    }

    public static String encode(String value) {
        Objects.requireNonNull(value, "value is required");
        return URLEncoder.encode(value, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
    }

    private static String queryString(MultiValuedMap<String, String> queryParams) {
        StringBuilder output = new StringBuilder();
        for (Map.Entry<String, Collection<String>> entry : queryParams.asMap().entrySet()) {
            String name = entry.getKey();
            String values = entry.getValue().stream()
                    .collect(Collectors.joining(","));
            output.append(output.indexOf("?") == -1 ? "?" : "&")
                    .append(name).append("=").append(values);
        }
        return output.toString();
    }

}