package com.jamesjss.retail.prices.infrastructure.adapter;

import com.jamesjss.retail.prices.application.exception.PriceNotFoundException;
import com.jamesjss.retail.prices.application.repository.PricesRepository;
import com.jamesjss.retail.prices.domain.model.Prices;
import com.jamesjss.retail.prices.infrastructure.mapper.PricesMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PricesRepositoryImpl implements PricesRepository {


    private final PricesJpaRepository pricesJpaRepository;
    private final PricesMapper pricesMapper;

    public PricesRepositoryImpl(PricesJpaRepository pricesJpaRepository, PricesMapper pricesMapper) {
        this.pricesJpaRepository = pricesJpaRepository;
        this.pricesMapper = pricesMapper;
    }


    @Override
    public List<Prices> searchByDateProductAndBrand(LocalDateTime dateBetween, Long productId, Long brandId) throws PriceNotFoundException {
        List<Prices> prices = pricesMapper.toPrices(pricesJpaRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, dateBetween, dateBetween));

        if (prices.isEmpty()) {
            throw new PriceNotFoundException("No prices were found for the parameters provided");
        }

        return prices;
    }

}
