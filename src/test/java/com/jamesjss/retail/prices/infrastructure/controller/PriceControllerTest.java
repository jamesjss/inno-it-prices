package com.jamesjss.retail.prices.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jamesjss.retail.prices.infrastructure.dto.PriceDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String BASE_URL = "/api/prices";

    @DisplayName("Request at 10:00 a.m. on the 14th for product 35455 for brand 1")
    @Test
    public void testSearchPriceAtTime_2020_06_14_10_00_00() throws Exception {


        //Date of request
        String dateTimeStr = "2020-06-14-10.00.00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDateTime targetDateTime = LocalDateTime.parse(dateTimeStr, formatter);

        Long targetProductId = 35455L; // productId
        Long targetBrandId = 1L; // brandId

        // Perform HTTP request using MockMvc
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("date", dateTimeStr)
                        .param("product", String.valueOf(targetProductId))
                        .param("brand", String.valueOf(targetBrandId)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //Transformation from json to Price List
        String responseContent = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        PriceDto priceResponse = objectMapper.readValue(responseContent, PriceDto.class);


        assertTrue(targetDateTime.isAfter(priceResponse.getStartDate()) &&
                targetDateTime.isBefore(priceResponse.getEndDate()) &&
                targetBrandId.equals(priceResponse.getBrandId()) &&
                targetProductId.equals(priceResponse.getProductId()));

    }


    @DisplayName("Request at 16:00 on the 14th of the day for product 35455 for brand 1")
    @Test
    public void testSearchPriceAtTime_2020_06_14_16_00_00() throws Exception {

        //Date of request
        String dateTimeStr = "2020-06-14-16.00.00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDateTime targetDateTime = LocalDateTime.parse(dateTimeStr, formatter);

        Long targetProductId = 35455L; // productId
        Long targetBrandId = 1L; // brandId

        // Perform HTTP request using MockMvc
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("date", dateTimeStr)
                        .param("product", String.valueOf(targetProductId))
                        .param("brand", String.valueOf(targetBrandId)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //Transformation from json to Price List
        String responseContent = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        PriceDto priceResponse = objectMapper.readValue(responseContent, PriceDto.class);


        assertTrue(targetDateTime.isAfter(priceResponse.getStartDate()) &&
                targetDateTime.isBefore(priceResponse.getEndDate()) &&
                targetBrandId.equals(priceResponse.getBrandId()) &&
                targetProductId.equals(priceResponse.getProductId()));

    }


    @DisplayName("Request at 21:00 on the 14th of the day of the product 35455 for brand 1")
    @Test
    public void testSearchPriceAtTime_2020_06_14_21_00_00() throws Exception {

        //Date of request
        String dateTimeStr = "2020-06-14-21.00.00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDateTime targetDateTime = LocalDateTime.parse(dateTimeStr, formatter);

        Long targetProductId = 35455L; // productId
        Long targetBrandId = 1L; // brandId

        // Perform HTTP request using MockMvc
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("date", dateTimeStr)
                        .param("product", String.valueOf(targetProductId))
                        .param("brand", String.valueOf(targetBrandId)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //Transformation from json to Price List
        String responseContent = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        PriceDto priceResponse = objectMapper.readValue(responseContent, PriceDto.class);


        assertTrue(targetDateTime.isAfter(priceResponse.getStartDate()) &&
                targetDateTime.isBefore(priceResponse.getEndDate()) &&
                targetBrandId.equals(priceResponse.getBrandId()) &&
                targetProductId.equals(priceResponse.getProductId()));

    }


    @DisplayName("Request at 10:00 on the 15th of the day of the product 35455 for brand 1")
    @Test
    public void testSearchPriceAtTime_2020_06_15_10_00_00() throws Exception {

        //Date of request
        String dateTimeStr = "2020-06-15-10.00.00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDateTime targetDateTime = LocalDateTime.parse(dateTimeStr, formatter);

        Long targetProductId = 35455L; // productId
        Long targetBrandId = 1L; // brandId

        // Perform HTTP request using MockMvc
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("date", dateTimeStr)
                        .param("product", String.valueOf(targetProductId))
                        .param("brand", String.valueOf(targetBrandId)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //Transformation from json to Price List
        String responseContent = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        PriceDto priceResponse = objectMapper.readValue(responseContent, PriceDto.class);


        assertTrue(targetDateTime.isAfter(priceResponse.getStartDate()) &&
                targetDateTime.isBefore(priceResponse.getEndDate()) &&
                targetBrandId.equals(priceResponse.getBrandId()) &&
                targetProductId.equals(priceResponse.getProductId()));


    }


    @DisplayName("Request at 21:00 on the 16th of the day of product 35455 for brand 1")
    @Test
    public void testSearchPriceAtTime_2020_06_16_21_00_00() throws Exception {

        //Date of request
        String dateTimeStr = "2020-06-16-21.00.00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDateTime targetDateTime = LocalDateTime.parse(dateTimeStr, formatter);

        Long targetProductId = 35455L; // productId
        Long targetBrandId = 1L; // brandId

        // Perform HTTP request using MockMvc
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("date", dateTimeStr)
                        .param("product", String.valueOf(targetProductId))
                        .param("brand", String.valueOf(targetBrandId)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //Transformation from json to Price List
        String responseContent = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        PriceDto priceResponse = objectMapper.readValue(responseContent, PriceDto.class);


        assertTrue(targetDateTime.isAfter(priceResponse.getStartDate()) &&
                targetDateTime.isBefore(priceResponse.getEndDate()) &&
                targetBrandId.equals(priceResponse.getBrandId()) &&
                targetProductId.equals(priceResponse.getProductId()));


    }


    @DisplayName("Testing of exception PriceNotFoundException")
    @Test
    public void testPriceNotFoundException() throws Exception {
        // Attempts to find a price on a date where it does not exist
        String dateTimeStr = "2020-06-14-08.00.00";
        Long targetProductId = 999L; //Non-existent product
        Long targetBrandId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("date", dateTimeStr)
                        .param("product", String.valueOf(targetProductId))
                        .param("brand", String.valueOf(targetBrandId)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @DisplayName("Testing Future Date")
    @Test
    public void testUpperDateLimit() throws Exception {
        // Try to find a price at a future date (upper limit).
        String dateTimeStr = "2030-12-31-23.59.59"; // Date in the future
        Long targetProductId = 35455L;
        Long targetBrandId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("date", dateTimeStr)
                        .param("product", String.valueOf(targetProductId))
                        .param("brand", String.valueOf(targetBrandId)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @DisplayName("Testing Past Date")
    @Test
    public void testLowerDateLimit() throws Exception {
        // Try to find a price at a past date (lower limit).
        String dateTimeStr = "2012-12-31-23.59.59"; // Date in the future
        Long targetProductId = 35455L;
        Long targetBrandId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("date", dateTimeStr)
                        .param("product", String.valueOf(targetProductId))
                        .param("brand", String.valueOf(targetBrandId)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @DisplayName("Testing Invalid Date Format")
    @Test
    public void testInvalidDateFormat() throws Exception {
        // Attempts to search for a price with an incorrect date format
        String dateTimeStr = "2020/06/14 08:00:00"; // Incorrect format
        Long targetProductId = 35455L;
        Long targetBrandId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("date", dateTimeStr)
                        .param("product", String.valueOf(targetProductId))
                        .param("brand", String.valueOf(targetBrandId)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @DisplayName("Testing Invalid Product Format")
    @Test
    public void testInvalidProductFormat() throws Exception {
        // Attempts to search for a price with an incorrect date format
        String dateTimeStr = "2020-06-14-08.00.00";
        String targetProductId = "ProductText"; // Incorrect format
        Long targetBrandId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("date", dateTimeStr)
                        .param("product", targetProductId)
                        .param("brand", String.valueOf(targetBrandId)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @DisplayName("Testing Invalid Brand Format")
    @Test
    public void testInvalidPBrandFormat() throws Exception {
        // Attempts to search for a price with an incorrect date format
        String dateTimeStr = "2020-06-14-08.00.00";
        Long targetProductId = 35455L;
        String targetBrandId = "BrandText"; // Incorrect format

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("date", dateTimeStr)
                        .param("product", String.valueOf(targetProductId))
                        .param("brand", targetBrandId))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @DisplayName("Test Missing Request parameters")
    @Test
    public void testMissingRequestParameters() throws Exception {
        // Attempts to search witouth parameters

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }



}