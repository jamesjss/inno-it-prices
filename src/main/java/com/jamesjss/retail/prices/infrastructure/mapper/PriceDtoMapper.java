package com.jamesjss.retail.prices.infrastructure.mapper;

import com.jamesjss.retail.prices.domain.model.Price;
import com.jamesjss.retail.prices.infrastructure.dto.PriceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PriceDtoMapper {
    @Mappings(
            {
                    @Mapping(source = "productId", target = "productId"),
                    @Mapping(source = "brandId", target = "brandId"),
                    @Mapping(source = "priceList", target = "priceList"),
                    @Mapping(source = "startDate", target = "startDate"),
                    @Mapping(source = "endDate", target = "endDate"),
                    @Mapping( target = "price", expression = "java(price.getPrice().toString() + \" \" + price.getCurr())")

            }

    )
    PriceDto toPrice(Price price);

}

