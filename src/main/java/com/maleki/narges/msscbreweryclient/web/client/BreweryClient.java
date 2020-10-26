package com.maleki.narges.msscbreweryclient.web.client;

import com.maleki.narges.msscbreweryclient.web.model.BeerDto;
import com.maleki.narges.msscbreweryclient.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.UUID;

@Slf4j
@Component
@ConfigurationProperties(prefix = "sfg.brewery",ignoreUnknownFields = false)
public class BreweryClient {




    private final RestTemplate restTemplate;
    private String apiHost;
    public final String BEER_PATH_V1 = "/api/v1/beer/";
    private final String CUSTOMER_PATH_V1 = "/api/v1/customer/";


    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }


    public BreweryClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();

    }


    public BeerDto getBeerbyId(UUID beerId){

        String url = apiHost + BEER_PATH_V1  + beerId.toString();
        log.debug("**********"+ url);
        return restTemplate.getForObject(url,BeerDto.class);

    }

    public void deleteBeer(UUID beerId){
        String url = apiHost + BEER_PATH_V1  + beerId.toString();
        restTemplate.delete(url);
    }



    public URI saveNewBeer(BeerDto beerDto){

        String url = apiHost + BEER_PATH_V1  ;
        log.debug("**********"+ url);
        return restTemplate.postForLocation(url,beerDto);


    }

    public void updateBeer(UUID beerId, BeerDto beerDto){
        String url = apiHost + BEER_PATH_V1 + beerId ;
        restTemplate.put(url,beerDto);
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
