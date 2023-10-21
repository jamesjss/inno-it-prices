package com.jamesjss.retail.prices.infrastructure.controller;

import com.jamesjss.retail.prices.application.services.PriceServiceUserCase;
import com.jamesjss.retail.prices.application.services.PricesService;
import com.jamesjss.retail.prices.domain.model.Prices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/prices")
public class PricesController {


    @Autowired
    PricesService pricesServiceImpl;

    //Input port
    private final PriceServiceUserCase priceServiceUserCase;

    public PricesController(PriceServiceUserCase priceServiceUserCase) {
        this.priceServiceUserCase = priceServiceUserCase;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Prices>> getPrices(
            @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd-HH.mm.ss") String startDate,
            @RequestParam("product") Long productId,
            @RequestParam("brand") Long brandId)  {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
            LocalDateTime dateTime = LocalDateTime.parse(startDate, formatter);

            List<Prices> prices = priceServiceUserCase.searchByDateProductAndBrand(dateTime, productId, brandId);
            return ResponseEntity.ok(prices);

    }

}