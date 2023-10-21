package com.jamesjss.retail.prices.application.services;

import com.jamesjss.retail.prices.domain.model.Prices;

import java.time.LocalDateTime;
import java.util.List;

//Input Port
public interface PriceServiceUserCase {
    List<Prices> searchByDateProductAndBrand(LocalDateTime dateBetween, Long productId, Long brandId);
}
