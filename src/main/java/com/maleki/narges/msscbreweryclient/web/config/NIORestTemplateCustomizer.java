package com.maleki.narges.msscbreweryclient.web.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//@Component
public class NIORestTemplateCustomizer implements RestTemplateCustomizer {
    @Override
    public void customize(RestTemplate restTemplate) {
        CloseableHttpAsyncClient asyncClinet;
        asyncClinet = HttpAsyncClientBuilder.create().build();
        restTemplate.setRequestFactory(new HttpComponentsAsyncClientHttpRequestFactory(asyncClinet));
    }
}
