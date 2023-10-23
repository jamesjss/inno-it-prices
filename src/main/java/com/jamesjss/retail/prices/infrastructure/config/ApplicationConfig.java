package com.jamesjss.retail.prices.infrastructure.config;

import com.jamesjss.retail.prices.application.repository.PriceRepository;
import com.jamesjss.retail.prices.application.services.PriceService;
import com.jamesjss.retail.prices.application.services.PriceServiceUserCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    PriceServiceUserCase pricesBeanService(final PriceRepository priceRepository) {
        return new PriceService(priceRepository);
    }
}
