package com.jamesjss.retail.prices.application.services;

import com.jamesjss.retail.prices.application.exception.PriceNotFoundException;
import com.jamesjss.retail.prices.domain.model.Prices;

import java.time.LocalDateTime;
import java.util.List;

//Input Port
public interface PriceServiceUserCase {
    Prices searchByDateProductAndBrand(LocalDateTime dateBetween, Long productId, Long brandId) throws PriceNotFoundException;
}
