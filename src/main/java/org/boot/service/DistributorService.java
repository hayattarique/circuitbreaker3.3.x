package org.boot.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.boot.dto.DistributorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DistributorService {
    private final RestTemplate restTemplate;
    //private final CircuitBreakerFactory circuitBreakerFactory;
    private final String BASE_URL = "http://distributor-network/distributor/{distributorCode}";



    /* public DistributorDto getDistributor(String distributorCode) {
     *//*circuitBreaker = circuitBreakerFactory.create("distributor-network");
        return circuitBreaker.run(() -> restTemplate.getForObject(BASE_URL, DistributorDto.class, distributorCode));*//*
        return restTemplate.getForObject(BASE_URL, DistributorDto.class, distributorCode);
    }

    public DistributorDto fallback(String distributorCode,Throwable throwable) {
        System.out.println("fallbackMethod " + throwable.getMessage());
        return DistributorDto.builder().build();
    }*/

    @CircuitBreaker(name = "distributor-network", fallbackMethod = "fallBack")
    public String getDistributorName(String distributorCode) {
        DistributorDto dto = restTemplate.getForObject(BASE_URL, DistributorDto.class, distributorCode);

        String distributorName = dto.getDistributorName();
        return distributorName;
    }

    public String fallBack(String distributorCode, Throwable t) {
        return distributorCode + t.getMessage();

    }


}
