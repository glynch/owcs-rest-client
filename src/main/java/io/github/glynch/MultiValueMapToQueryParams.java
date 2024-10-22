package io.github.glynch;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

public class MultiValueMapToQueryParams {

    public static String convertToQueryParams(MultiValuedMap<String, String> multiValueMap) {
        StringBuilder queryParams = new StringBuilder();

        for (Map.Entry<String, Collection<String>> entry : multiValueMap.asMap().entrySet()) {
            String key = entry.getKey();
            Collection<String> values = entry.getValue();

            // Join multiple values with a comma
            List<String> items = new ArrayList<>(values);
            String output = items.stream()
                    .map(MultiValueMapToQueryParams::urlEncode)
                    .collect(Collectors.joining(","));
            System.out.println(output);
            String joinedValues = String.join(",", values);

            try {
                if (queryParams.length() > 0) {
                    queryParams.append("&");
                }
                queryParams.append(URLEncoder.encode(key, StandardCharsets.UTF_8.toString()));
                queryParams.append("=");
                queryParams.append(URLEncoder.encode(joinedValues, StandardCharsets.UTF_8.toString()));
            } catch (UnsupportedEncodingException e) {
                // Handle the exception as needed
                e.printStackTrace();
            }
        }
        return queryParams.toString();
    }

    private static String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 encoding not supported", e);
        }
    }

    public static void main(String[] args) {
        // Example usage
        MultiValuedMap<String, String> multiValueMap = new ArrayListValuedHashMap<>();
        multiValueMap.put("param1", "Running Recommendation");
        multiValueMap.put("param1", "foo bar baz's");
        multiValueMap.put("param2", "value3");

        String queryParams = convertToQueryParams(multiValueMap);
        System.out.println(queryParams); // Output: param1=value1,value2&param2=value3

        System.out.println(URLEncoder.encode(
                "http://localhost:7003/sites/REST/resources/v1/sites/recommendation/Running Recommendation",
                StandardCharsets.UTF_8)); // Output: %20
    }

}