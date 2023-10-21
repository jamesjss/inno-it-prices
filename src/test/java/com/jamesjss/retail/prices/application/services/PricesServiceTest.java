package com.jamesjss.retail.prices.application.services;

import com.jamesjss.retail.prices.application.repository.PricesRepository;
import com.jamesjss.retail.prices.domain.model.Prices;
import com.jamesjss.retail.prices.application.services.PricesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)

public class PricesServiceTest {

    @Mock
    private PricesRepository pricesRepository;

    @InjectMocks
    private PricesService pricesService;


    @Test
    public void testGetByDateProductAndBrand() {
        // Test data
        LocalDateTime dateBetween = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 1L;
        Prices expectedPrice1 = new Prices(1L, 1L, 1, LocalDateTime.now(), dateBetween, new BigDecimal("35.50"));
        Prices expectedPrice2 = new Prices(2L, 1L, 1, LocalDateTime.now(), dateBetween, new BigDecimal("40.00"));
        List<Prices> expectedPrices = Arrays.asList(expectedPrice1, expectedPrice2);

        // Configures the behavior of the mock repository
        Mockito.when(pricesRepository.searchByDateProductAndBrand(dateBetween, productId, brandId))
                .thenReturn(expectedPrices);

        // Call the method under test
        List<Prices> result = pricesService.searchByDateProductAndBrand(dateBetween, productId, brandId);

        // Verify that the results are as expected
        assertEquals(expectedPrices.size(), result.size());
        assertArrayEquals(expectedPrices.toArray(), result.toArray());


        // Verifies that the repository method was called with the correct arguments
        Mockito.verify(pricesRepository).searchByDateProductAndBrand(dateBetween, productId, brandId);
    }
}
