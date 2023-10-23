package com.jamesjss.retail.prices.infrastructure.adapter;

import com.jamesjss.retail.prices.application.exception.PriceNotFoundException;
import com.jamesjss.retail.prices.application.repository.PricesRepository;
import com.jamesjss.retail.prices.domain.model.Prices;
import com.jamesjss.retail.prices.infrastructure.entity.PricesEntity;
import com.jamesjss.retail.prices.infrastructure.mapper.PricesMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class PricesRepositoryImpl implements PricesRepository {


    private final PricesJpaRepository pricesJpaRepository;
    private final PricesMapper pricesMapper;

    public PricesRepositoryImpl(PricesJpaRepository pricesJpaRepository, PricesMapper pricesMapper) {
        this.pricesJpaRepository = pricesJpaRepository;
        this.pricesMapper = pricesMapper;
    }


    @Override
    public Prices searchByDateProductAndBrand(LocalDateTime dateBetween, Long productId, Long brandId) throws PriceNotFoundException {

        Optional<PricesEntity> priceResult = Optional.ofNullable(pricesJpaRepository.findByBrandIdAndProductId(brandId, productId)
                .stream()
                .filter(price -> dateBetween.isAfter(price.getStartDate()) && dateBetween.isBefore(price.getEndDate()))
                .sorted(Comparator.comparing(PricesEntity::getPriority).reversed())
                .findFirst()
                .orElseThrow(
                        () -> new PriceNotFoundException("No prices were found for the parameters provided")
                ));

        return pricesMapper.toPrices(priceResult.get());
    }

}
