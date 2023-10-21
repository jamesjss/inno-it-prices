package com.jamesjss.retail.prices.infrastructure.adapter;

import com.jamesjss.retail.prices.application.exception.PriceNotFoundException;
import com.jamesjss.retail.prices.domain.model.Prices;
import com.jamesjss.retail.prices.infrastructure.entity.PricesEntity;
import com.jamesjss.retail.prices.infrastructure.mapper.PricesMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PricesRepositoryImplTest {

    @InjectMocks
    private PricesRepositoryImpl pricesRepository;

    @Mock
    private PricesJpaRepository pricesJpaRepository;

    @Mock
    private PricesMapper pricesMapper;

    @Test
    public void testSearchByDateProductAndBrand() throws PriceNotFoundException {
        // Prepare test data and expected behavior for the repository and mapper

        // Define test objects
        LocalDateTime localDateTime = LocalDateTime.now().plusHours(1);
        PricesEntity entity = new PricesEntity(
                1L,
                1L,
                LocalDateTime.now().minusHours(1),
                LocalDateTime.now().plusHours(1),
                1,
                35455L,
                0,
                new BigDecimal("35.50"),
                "EUR");

        Prices expectedPrices = new Prices(
                35455L,
                1L,
                1,
                LocalDateTime.now().minusHours(1),
                LocalDateTime.now().plusHours(1),
                new BigDecimal("35.50"));

        // Configure the behavior of the pricesJpaRepository mock
        when(pricesJpaRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                1L, 1L, localDateTime, localDateTime))
                .thenReturn(Collections.singletonList(entity));

        // Configure the behavior of the pricesMapper mock
        when(pricesMapper.toPrices(Collections.singletonList(entity)))
                .thenReturn(Collections.singletonList(expectedPrices));

        // Execute the method under test
        List<Prices> result = pricesRepository.searchByDateProductAndBrand(localDateTime, 1L, 1L);

        // Verify that the expected results are obtained
        assertEquals(1, result.size());
        assertEquals(expectedPrices, result.get(0));
    }
}