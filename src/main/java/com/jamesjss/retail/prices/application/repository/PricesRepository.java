package com.jamesjss.retail.prices.application.repository;

import com.jamesjss.retail.prices.domain.model.Prices;

import java.time.LocalDateTime;
import java.util.List;

//Output Port
public interface PricesRepository {
    List<Prices> searchByDateProductAndBrand(LocalDateTime dateBetween, Integer productId, Integer brandId);
}
