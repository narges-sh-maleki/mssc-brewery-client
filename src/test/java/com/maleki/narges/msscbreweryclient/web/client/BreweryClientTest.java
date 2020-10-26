package com.maleki.narges.msscbreweryclient.web.client;

import com.maleki.narges.msscbreweryclient.web.model.BeerDto;
import com.maleki.narges.msscbreweryclient.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient breweryClient;

    @Test
    void getBeerbyId() {
      BeerDto beerDto =  breweryClient.getBeerbyId(UUID.randomUUID());
      assertNotNull(beerDto);

    }

    @Test
    void saveNewBeer() {
        BeerDto beerDto = BeerDto.builder().build();
        URI uri = breweryClient.saveNewBeer(beerDto);
        assertNotNull(uri);
        log.debug(uri.toString());
    }

    @Test
    void updateBeer() {
        BeerDto beerDto = BeerDto.builder().build();
        breweryClient.updateBeer(UUID.randomUUID(),beerDto);
    }

    @Test
    void deleteBeer() {
        breweryClient.deleteBeer(UUID.randomUUID());
    }


    @Test
    void getCustomerById() {
        CustomerDto customerDto =  breweryClient.getCustomerById(UUID.randomUUID());
        assertNotNull(customerDto);
    }

    @Test
    void saveNewCustomer() {

        CustomerDto customerDto =  breweryClient.saveNewCustomer(CustomerDto.builder().id(UUID.randomUUID()).build());
        assertNotNull(customerDto);
    }

    @Test
    void updateCustomer() {
        breweryClient.updateCustomer(UUID.randomUUID(),CustomerDto.builder().build());
    }

    @Test
    void deleteCustomer() {
        breweryClient.deleteCustomer(UUID.randomUUID());
    }

}