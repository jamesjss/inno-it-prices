package com.jamesjss.retail.prices.application.services;

import com.jamesjss.retail.prices.application.exception.PriceNotFoundException;
import com.jamesjss.retail.prices.application.repository.PriceRepository;
import com.jamesjss.retail.prices.domain.model.Price;

import java.time.LocalDateTime;

public class PriceService implements PriceServiceUserCase {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price searchByDateProductAndBrand(LocalDateTime dateBetween, Long productId, Long brandId) throws PriceNotFoundException {
        return priceRepository.searchByDateProductAndBrand(dateBetween, productId, brandId);
    }


}

