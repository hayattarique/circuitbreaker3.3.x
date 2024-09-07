package org.boot.service;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.boot.dto.DistributorDto;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class DistributorService {
    private final RestTemplate restTemplate;
    //private final CircuitBreakerFactory circuitBreakerFactory;
    private final String BASE_URL = "http://distributor-network/distributor/{distributorCode}";


    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "distributor-network", fallbackMethod = "fallBackMethod")
    public DistributorDto getDistributor(String distributorCode) {
        CircuitBreaker circuitBreaker = null;
        /*circuitBreaker = circuitBreakerFactory.create("distributor-network");
        return circuitBreaker.run(() -> restTemplate.getForObject(BASE_URL, DistributorDto.class, distributorCode));*/
        return restTemplate.getForObject(BASE_URL, DistributorDto.class, distributorCode);
    }

    public String fallbackMethod(Throwable throwable) {
        return throwable.getMessage();
    }
}
