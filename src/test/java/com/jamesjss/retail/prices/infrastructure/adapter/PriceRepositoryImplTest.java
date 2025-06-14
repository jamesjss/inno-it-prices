package com.jamesjss.retail.prices.infrastructure.adapter;

import com.jamesjss.retail.prices.application.exception.PriceNotFoundException;
import com.jamesjss.retail.prices.domain.model.Price;
import com.jamesjss.retail.prices.infrastructure.entity.PriceEntity;
import com.jamesjss.retail.prices.infrastructure.mapper.PriceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class PriceRepositoryImplTest {

    private PriceRepositoryImpl priceRepository;

    @Mock
    private PriceJpaRepository priceJpaRepository;

    @Mock
    private PriceMapper priceMapper;


    private static PriceEntity priceEntity1;
    private static PriceEntity priceEntity2;
    private static PriceEntity priceEntity3;
    private static PriceEntity priceEntity4;

    private static Price expectedPrice;

    @BeforeEach
    public void setUp() {
        priceRepository = new PriceRepositoryImpl(priceJpaRepository, priceMapper);

        priceEntity1 = PriceEntity.builder()
                .brandId(1L)
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .priceList(1)
                .productId(35455L)
                .priority(0)
                .price(new BigDecimal("35.50"))
                .curr("EUR")
                .build();

        priceEntity2 = PriceEntity.builder()
                .brandId(1L)
                .startDate(LocalDateTime.parse("2020-06-14T15:00:00"))
                .endDate(LocalDateTime.parse("2020-06-14T18:30:00"))
                .priceList(2)
                .productId(35455L)
                .priority(1)
                .price(new BigDecimal("25.45"))
                .curr("EUR")
                .build();

        priceEntity3 = PriceEntity.builder()
                .brandId(1L)
                .startDate(LocalDateTime.parse("2020-06-15T00:00:00"))
                .endDate(LocalDateTime.parse("2020-06-15T11:00:00"))
                .priceList(3)
                .productId(35455L)
                .priority(1)
                .price(new BigDecimal("30.50"))
                .curr("EUR")
                .build();

        priceEntity4 = PriceEntity.builder()
                .brandId(1L)
                .startDate(LocalDateTime.parse("2020-06-15T16:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .priceList(4)
                .productId(35455L)
                .priority(1)
                .price(new BigDecimal("38.95"))
                .curr("EUR")
                .build();
    }


    @DisplayName("Test to search data and retrieve results")
    @Test
    public void testSearchByDateProductAndBrand_WithResults() throws PriceNotFoundException {
        LocalDateTime dateBetween = LocalDateTime.parse("2020-06-14T10:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        expectedPrice = Price.builder()
                .productId(35455L)
                .brandId(1L)
                .priceList(2)
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .price(new BigDecimal("35.50"))
                .curr("EUR")
                .build();


        //We create a list with four values
        List<PriceEntity> priceEntities = List.of(priceEntity1, priceEntity2, priceEntity3, priceEntity4);

        // Simulate that the query returns results
        Mockito.when(priceJpaRepository.findByBrandIdAndProductId(brandId, productId)).thenReturn(priceEntities);

        //  Simulate mapping to Price
        Mockito.when(priceMapper.toPrice(Mockito.any(PriceEntity.class))).thenReturn(expectedPrice);

        Price result = priceRepository.searchByDateProductAndBrand(dateBetween, productId, brandId);

        // Check that the same result as expected is returned.
        assertEquals(expectedPrice, result);
    }


    @DisplayName("Test to search data and retrieve results with the highest priority")
    @Test
    public void testSearchByDateProductAndBrand_WithResults_And_Priority() throws PriceNotFoundException {
        LocalDateTime dateBetween = LocalDateTime.parse("2020-06-14T16:00:00");
        Long productId = 35455L;
        Long brandId = 1L;



        expectedPrice = Price.builder()
                .productId(35455L)
                .brandId(1L)
                .priceList(2)
                .startDate(LocalDateTime.parse("2020-06-14T15:00:00"))
                .endDate(LocalDateTime.parse("2020-06-14T18:30:00"))
                .price(new BigDecimal("25.45"))
                .curr("EUR")
                .build();


        //We create a list with two values, priceEntity1 has priority 0 (lower priority), priceEntity2 has priority 1 (higher priority).
        List<PriceEntity> priceEntities = List.of(priceEntity1, priceEntity2);

        // Simulate that the query returns results
        Mockito.when(priceJpaRepository.findByBrandIdAndProductId(brandId, productId)).thenReturn(priceEntities);

        //  Simulate mapping to Price
        Mockito.when(priceMapper.toPrice(Mockito.any(PriceEntity.class))).thenReturn(expectedPrice);

        Price result = priceRepository.searchByDateProductAndBrand(dateBetween, productId, brandId);

        // Check that the same result as expected is returned.
        assertEquals(expectedPrice, result);

        // Check that the priority is as expected
        assertThat(priceEntity2.getPriority()).isEqualTo(1);
    }


    @DisplayName("Test No Results and PriceNotFoundException is thrown")
    @Test
    public void testSearchByDateProductAndBrandNoResults() {
        LocalDateTime dateBetween = LocalDateTime.parse("2020-06-14T10:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        // Simulate that the query does not return results
        Mockito.when(priceJpaRepository.findByBrandIdAndProductId(brandId, productId)).thenReturn(Collections.emptyList());

        // Verify that a PriceNotFoundException is thrown
        assertThrows(PriceNotFoundException.class, () -> priceRepository.searchByDateProductAndBrand(dateBetween, productId, brandId));
    }

    @DisplayName("Testing Future Date. No date Returned")
    @Test
    public void testSearchByDateProductAndBrand_Future_Date() {
        LocalDateTime dateBetween = LocalDateTime.parse("2050-06-14T10:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        // Simulate that the query does not return results
        Mockito.when(priceJpaRepository.findByBrandIdAndProductId(brandId, productId)).thenReturn(Collections.emptyList());

        // Verify that a PriceNotFoundException is thrown
        assertThrows(PriceNotFoundException.class, () -> priceRepository.searchByDateProductAndBrand(dateBetween, productId, brandId));
    }


}
