package com.jamesjss.retail.prices.infrastructure.adapter;

import com.jamesjss.retail.prices.infrastructure.entity.PricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PricesJpaRepository extends JpaRepository<PricesEntity, Long> {
    List<PricesEntity> findByBrandIdAndProductId(Long brandId, Long productId);
}
