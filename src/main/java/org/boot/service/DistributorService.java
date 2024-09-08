package org.boot.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.boot.dto.DistributorDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class DistributorService {
    private final RestTemplate restTemplate;

    private final String BASE_URL = "http://distributor-network/distributor/{distributorCode}";


    @CircuitBreaker(name = "distributor-network", fallbackMethod = "getDistributorFallBack")

    public DistributorDto getDistributor(String distributorCode) {
        return restTemplate.getForObject(BASE_URL, DistributorDto.class, distributorCode);
    }

    public DistributorDto getDistributorFallBack(Throwable t) {
        log.info("server-down");
        return null;
    }


    @CircuitBreaker(name = "distributor-network", fallbackMethod = "fallBackGetDistributorName")
    public String getDistributorName(String distributorCode) {
        DistributorDto dto = restTemplate.getForObject(BASE_URL, DistributorDto.class, distributorCode);
        if (dto != null) {
            return dto.getDistributorName();
        }
        return null;
    }

    public String fallBackGetDistributorName(String distributorCode, Throwable t) {
        return distributorCode + t.getMessage();

    }


}
