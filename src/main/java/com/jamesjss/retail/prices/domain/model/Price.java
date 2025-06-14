package com.jamesjss.retail.prices.domain.model;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record Price(
    Long brandId,
    LocalDateTime startDate,
    LocalDateTime endDate,
    int priceList,
    Long productId,
    int priority,
    BigDecimal price,
    String curr
) {}