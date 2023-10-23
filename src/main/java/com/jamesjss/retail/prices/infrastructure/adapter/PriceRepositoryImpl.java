package com.jamesjss.retail.prices.infrastructure.adapter;

import com.jamesjss.retail.prices.application.exception.PriceNotFoundException;
import com.jamesjss.retail.prices.application.repository.PriceRepository;
import com.jamesjss.retail.prices.domain.model.Price;
import com.jamesjss.retail.prices.infrastructure.entity.PriceEntity;
import com.jamesjss.retail.prices.infrastructure.mapper.PricesMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

@Repository
public class PriceRepositoryImpl implements PriceRepository {


    private final PriceJpaRepository priceJpaRepository;
    private final PricesMapper pricesMapper;

    public PriceRepositoryImpl(PriceJpaRepository priceJpaRepository, PricesMapper pricesMapper) {
        this.priceJpaRepository = priceJpaRepository;
        this.pricesMapper = pricesMapper;
    }


    @Override
    public Price searchByDateProductAndBrand(LocalDateTime dateBetween, Long productId, Long brandId) throws PriceNotFoundException {

        Optional<PriceEntity> priceResult = Optional.ofNullable(priceJpaRepository.findByBrandIdAndProductId(brandId, productId)
                .stream()
                .filter(price -> dateBetween.isAfter(price.getStartDate()) && dateBetween.isBefore(price.getEndDate()))
                .sorted(Comparator.comparing(PriceEntity::getPriority).reversed())
                .findFirst()
                .orElseThrow(
                        () -> new PriceNotFoundException("No prices were found for the parameters provided")
                ));

        return pricesMapper.toPrice(priceResult.get());
    }

}
