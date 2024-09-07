package org.boot;

import org.boot.dto.DistributorDto;
import org.boot.service.DistributorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CircuitBreakerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(CircuitBreakerApplication.class, args);
        DistributorService distributorService = context.getBean(DistributorService.class);
        for (int i = 1; i <= 1000; i++) {
            DistributorDto distributor = distributorService.getDistributor("D001");
            System.out.println(distributor);
        }
    }

    @Bean
  //  @LoadBalanced
    public RestTemplate resttemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> circuitBreakerCustomizer() {
//        return factory -> factory.configure(resilience4JConfigBuilder ->
//                resilience4JConfigBuilder.circuitBreakerConfig(CircuitBreakerConfig
//                                .custom()
//                                .failureRateThreshold(3).waitDurationInOpenState(Duration.ofSeconds(3))
//                                .slidingWindowSize(2).slowCallRateThreshold(3).build())
//                        .timeLimiterConfig(TimeLimiterConfig.custom()
//                                .timeoutDuration(Duration.ofSeconds(3)).build()), "distributor-network");
//    }


}
