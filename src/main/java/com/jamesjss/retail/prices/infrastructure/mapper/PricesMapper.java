package com.jamesjss.retail.prices.infrastructure.mapper;

import com.jamesjss.retail.prices.domain.model.Price;
import com.jamesjss.retail.prices.infrastructure.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PricesMapper {
    @Mappings(
            {
                    @Mapping(source = "productId", target = "productId"),
                    @Mapping(source = "brandId", target = "brandId"),
                    @Mapping(source = "priceList", target = "priceList"),
                    @Mapping(source = "priority", target = "priority"),
                    @Mapping(source = "startDate", target = "startDate"),
                    @Mapping(source = "endDate", target = "endDate"),
                    @Mapping(source = "price", target = "price")
            }

    )
    Price toPrice (PriceEntity priceEntity);

}