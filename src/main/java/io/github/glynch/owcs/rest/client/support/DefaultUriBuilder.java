package io.github.glynch.owcs.rest.client.support;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import io.github.glynch.owcs.rest.support.UriBuilder;

public class DefaultUriBuilder implements UriBuilder {

    private static final Pattern REPLACE_PATTERN = Pattern.compile("\\{([^/]+)\\}");
    private static final Pattern VARIABLES_PATTERN = Pattern.compile("\\{([^/]+)\\}");
    private final List<String> variables = new ArrayList<>();
    private final String uriTemplate;

    public DefaultUriBuilder(String uriTemplate) {
        this.uriTemplate = uriTemplate;
        var matcher = VARIABLES_PATTERN.matcher(uriTemplate);
        while (matcher.find()) {
            variables.add(matcher.group(1));
        }
    }

    @Override
    public URI build(Map<String, ?> uriVariables) {
        var output = new StringBuilder();
        var matcher = REPLACE_PATTERN.matcher(uriTemplate);
        int lastIndex = 0;
        while (matcher.find()) {
            var variable = matcher.group(1);
            output.append(uriTemplate, lastIndex, matcher.start()).append(uriVariables.get(variable));

            lastIndex = matcher.end();

        }
        if (lastIndex < uriTemplate.length()) {
            output.append(uriTemplate, lastIndex, uriTemplate.length());
        } else {
            // Do nothing
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
            output.append(uriTemplate, lastIndex, matcher.start()).append(uriVariables[index]);

            lastIndex = matcher.end();

        }
        if (lastIndex < uriTemplate.length()) {
            output.append(uriTemplate, lastIndex, uriTemplate.length());
        } else {
            // Do nothing
        }
        return URI.create(output.toString());
    }

}
