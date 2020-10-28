package com.maleki.narges.msscbreweryclient.web.config;

import lombok.RequiredArgsConstructor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
//ConfigurationProperties doesnt work with final variables
//@ConfigurationProperties(prefix = "httpclient.setting", ignoreUnknownFields = false)
@Component

public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    private final int connectionMaxtotal;
    private final int connectionMaxPerRoute;
    private final int requestTimeout;
    private final int socketTimeout;

    public BlockingRestTemplateCustomizer(@Value("${httpclient.setting.connectionMaxtotal}") int connectionMaxtotal,
                                          @Value("${httpclient.setting.connectionMaxPerRoute}") int connectionMaxPerRoute,
                                          @Value("${httpclient.setting.requestTimeout}") int requestTimeout,
                                          @Value("${httpclient.setting.socketTimeout}") int socketTimeout) {
        this.connectionMaxtotal = connectionMaxtotal;
        this.connectionMaxPerRoute = connectionMaxPerRoute;
        this.requestTimeout = requestTimeout;
        this.socketTimeout = socketTimeout;
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {


        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(connectionMaxtotal);
        connManager.setDefaultMaxPerRoute(connectionMaxPerRoute);

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(requestTimeout)
                .setSocketTimeout(socketTimeout)
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

        return httpComponentsClientHttpRequestFactory;
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}
