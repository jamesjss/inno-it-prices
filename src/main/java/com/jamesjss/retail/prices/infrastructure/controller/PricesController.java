package com.jamesjss.retail.prices.infrastructure.controller;

import com.jamesjss.retail.prices.application.exception.PriceNotFoundException;
import com.jamesjss.retail.prices.application.services.PriceServiceUserCase;
import com.jamesjss.retail.prices.application.services.PricesService;
import com.jamesjss.retail.prices.domain.model.Prices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api")
@Tag(name = "Prices", description = "Transactions related to product prices")

public class PricesController {

    //Input port
    private final PriceServiceUserCase priceServiceUserCase;

    public PricesController(PriceServiceUserCase priceServiceUserCase) {
        this.priceServiceUserCase = priceServiceUserCase;
    }

    @Tag(name = "Prices")
    @Operation(
            summary = "Get prices for a specific date, product and brand.",
            description = "Retrieves available prices for a specific date, product and brand.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Prices found"),
                    @ApiResponse(responseCode = "404", description = "Prices not found"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )
    @GetMapping("/prices")
    public ResponseEntity<List<Prices>> getPrices(
            @Parameter(description = "Date of request", example = "2020-06-14-10.00.00")
            @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd-HH.mm.ss") String startDate,

            @Parameter(description = "Product Id", example = "35455")
            @RequestParam("product") Long productId,

            @Parameter(description = "Brand Id", example = "1")
            @RequestParam("brand") Long brandId)

            throws PriceNotFoundException {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
            LocalDateTime dateTime = LocalDateTime.parse(startDate, formatter);

            List<Prices> prices = priceServiceUserCase.searchByDateProductAndBrand(dateTime, productId, brandId);
            return ResponseEntity.ok(prices);

    }

}