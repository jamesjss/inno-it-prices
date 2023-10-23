package com.jamesjss.retail.prices.application.services;

import com.jamesjss.retail.prices.application.exception.PriceNotFoundException;
import com.jamesjss.retail.prices.application.repository.PriceRepository;
import com.jamesjss.retail.prices.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;


    @Test
    public void testSearchByDateProductAndBrand_Success() throws PriceNotFoundException {
        // Data to test
        LocalDateTime date = LocalDateTime.now();
        Long productId = 123L;
        Long brandId = 456L;

        Price expectedPrice = Price.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(1)
                .priority(1)
                .startDate(date)
                .endDate(LocalDateTime.now())
                .price(new BigDecimal("35.50"))
                .curr("EUR").build();

        //When
        when(priceRepository.searchByDateProductAndBrand(date, productId, brandId))
                .thenReturn(expectedPrice);

        //Execution
        Price result = priceService.searchByDateProductAndBrand(date, productId, brandId);

        // Assert
        assertEquals(expectedPrice, result);
    }
}