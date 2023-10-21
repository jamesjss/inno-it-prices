package com.jamesjss.retail.prices.infrastructure.config;

import com.jamesjss.retail.prices.application.repository.PricesRepository;
import com.jamesjss.retail.prices.application.services.PriceServiceUserCase;
import com.jamesjss.retail.prices.application.services.PricesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    PriceServiceUserCase pricesBeanService(final PricesRepository pricesRepository) {
        return new PricesService(pricesRepository);
    }
}
