package com.maleki.narges.msscbreweryclient.web.client;

import com.maleki.narges.msscbreweryclient.web.model.BeerDto;
import com.maleki.narges.msscbreweryclient.web.model.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "sfg.customer",ignoreUnknownFields = false)
public class CustomerClient {

    private String apiHost ;
    private final String CUSTOMER_PATH_V1 = "/api/v1/customer/";

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public CustomerDto getCustomerById(UUID customerId){
        String url = apiHost + CUSTOMER_PATH_V1 + customerId.toString();
        return restTemplate.getForObject(url, CustomerDto.class);
    }

    public CustomerDto saveNewCustomer( CustomerDto customerDto){
        String url = apiHost + CUSTOMER_PATH_V1 ;
        ResponseEntity entity =  restTemplate.postForEntity(url,customerDto,CustomerDto.class);
        return (CustomerDto) entity.getBody();
    }

    public void updateCustomer(UUID customerId, CustomerDto customerDto){
        String url = apiHost + CUSTOMER_PATH_V1 + customerId ;
        restTemplate.put(url,customerDto);

    }

    public void deleteCustomer(UUID customerId){
        String url = apiHost + CUSTOMER_PATH_V1 + customerId ;
        restTemplate.delete(url);
    }



}
