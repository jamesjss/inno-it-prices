package com.jamesjss.retail.prices.application.services;

import com.jamesjss.retail.prices.application.exception.PriceNotFoundException;
import com.jamesjss.retail.prices.domain.model.Price;

import java.time.LocalDateTime;

//Input Port
public interface PriceServiceUserCase {
    Price searchByDateProductAndBrand(LocalDateTime dateBetween, Long productId, Long brandId) throws PriceNotFoundException;
}
