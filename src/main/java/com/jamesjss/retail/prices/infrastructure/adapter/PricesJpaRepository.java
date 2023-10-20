package com.jamesjss.retail.prices.infrastructure.adapter;

import com.jamesjss.retail.prices.infrastructure.entity.PricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PricesJpaRepository extends JpaRepository<PricesEntity, Long> {
    List<PricesEntity> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Integer brandId, Integer productId, LocalDateTime startDate, LocalDateTime endTime);
}
