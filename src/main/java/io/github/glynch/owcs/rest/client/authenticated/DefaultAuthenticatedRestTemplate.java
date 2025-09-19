package io.github.glynch.owcs.rest.client.authenticated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.glynch.owcs.rest.client.LoggingClientHttpRequestInterceptor;
import io.github.glynch.owcs.rest.support.DefaultObjectMapper;

public class DefaultAuthenticatedRestTemplate extends RestTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger("io.github.glynch.owcs.rest.client");

    private static final ObjectMapper objectMapper = new DefaultObjectMapper();
    private static final ClientHttpRequestInterceptor loggingInterceptor = new LoggingClientHttpRequestInterceptor();
    private static final ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler(objectMapper);
    private static final HttpMessageConverter<?> messageConverter = new MappingJackson2HttpMessageConverter(
            objectMapper);

    public DefaultAuthenticatedRestTemplate(ClientHttpRequestFactory requestFactory) {
        super();
        setErrorHandler(errorHandler);
        // Remove the default MappingJackson2HttpMessageConverter
        getMessageConverters().removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
        getMessageConverters().add(messageConverter);
        getInterceptors().add(loggingInterceptor);
        if (LOGGER.isTraceEnabled()) {
            setRequestFactory(new BufferingClientHttpRequestFactory(requestFactory));
        } else {
            setRequestFactory(requestFactory);
        }
    }

}
