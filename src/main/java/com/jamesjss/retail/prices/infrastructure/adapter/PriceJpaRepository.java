package com.jamesjss.retail.prices.infrastructure.adapter;

import com.jamesjss.retail.prices.infrastructure.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {
    List<PriceEntity> findByBrandIdAndProductId(Long brandId, Long productId);
}
