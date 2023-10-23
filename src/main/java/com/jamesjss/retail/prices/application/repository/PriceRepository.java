package com.jamesjss.retail.prices.application.repository;

import com.jamesjss.retail.prices.application.exception.PriceNotFoundException;
import com.jamesjss.retail.prices.domain.model.Price;

import java.time.LocalDateTime;

//Output Port
public interface PriceRepository {
    Price searchByDateProductAndBrand(LocalDateTime dateBetween, Long productId, Long brandId) throws PriceNotFoundException;
}
