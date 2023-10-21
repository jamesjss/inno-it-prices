package com.jamesjss.retail.prices.application.services;

import com.jamesjss.retail.prices.application.repository.PricesRepository;
import com.jamesjss.retail.prices.domain.model.Prices;

import java.time.LocalDateTime;
import java.util.List;

public class PricesService implements PriceServiceUserCase {

    private final PricesRepository pricesRepository;

    public PricesService(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    @Override
    public List<Prices> searchByDateProductAndBrand(LocalDateTime dateBetween, Long productId, Long brandId) {
        return pricesRepository.searchByDateProductAndBrand(dateBetween, productId, brandId);
    }


}

