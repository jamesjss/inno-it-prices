package com.jamesjss.retail.prices.infrastructure.mapper;

import com.jamesjss.retail.prices.domain.model.Prices;
import com.jamesjss.retail.prices.infrastructure.entity.PricesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PricesMapper {
    @Mappings(
            {
                    @Mapping(source = "productId", target = "productId"),
                    @Mapping(source = "brandId", target = "brandId"),
                    @Mapping(source = "priceList", target = "priceList"),
                    @Mapping(source = "startDate", target = "startDate"),
                    @Mapping(source = "endDate", target = "endDate"),
                    @Mapping(source = "price", target = "price")
            }

    )
    List<Prices> toPrices (List<PricesEntity> pricesEntity);

}