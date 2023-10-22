package com.jamesjss.retail.prices.infrastructure.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jamesjss.retail.prices.domain.model.Prices;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class PricesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String BASE_URL = "/api/prices/search";

    @DisplayName("First test required by the technical test")
    @Test
    public void testGetPriceAtSpecificTimeTest1() throws Exception {


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
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andReturn();

        //Transformation from json to Price List
        String responseContent = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        List<Prices> response = objectMapper.readValue(responseContent, new TypeReference<>() {});

        boolean allMatch = response.stream().allMatch( price -> {
            // Comparison of all values
            return  targetDateTime.isAfter(price.getStartDate()) &&
                    targetDateTime.isBefore(price.getEndDate()) &&
                    targetBrandId.equals(price.getBrandId()) &&
                    targetProductId.equals(price.getProductId());
        });

        //Assert of all previous comparisons
        assertTrue(allMatch);

    }


    @DisplayName("Second test required by the technical test")
    @Test
    public void testGetPriceAtSpecificTimeTest2() throws Exception {

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
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andReturn();

        //Transformation from json to Price List
        String responseContent = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        List<Prices> response = objectMapper.readValue(responseContent, new TypeReference<>() {});

        boolean allMatch = response.stream().allMatch( price -> {
            // Comparison of all values
            return  targetDateTime.isAfter(price.getStartDate()) &&
                    targetDateTime.isBefore(price.getEndDate()) &&
                    targetBrandId.equals(price.getBrandId()) &&
                    targetProductId.equals(price.getProductId());
        });

        //Assert of all previous comparisons
        assertTrue(allMatch);
    }


    @DisplayName("Third test required by the technical test")
    @Test
    public void testGetPriceAtSpecificTimeTest3() throws Exception {

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
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andReturn();

        //Transformation from json to Price List
        String responseContent = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        List<Prices> response = objectMapper.readValue(responseContent, new TypeReference<>() {});

        boolean allMatch = response.stream().allMatch( price -> {
            // Comparison of all values
            return  targetDateTime.isAfter(price.getStartDate()) &&
                    targetDateTime.isBefore(price.getEndDate()) &&
                    targetBrandId.equals(price.getBrandId()) &&
                    targetProductId.equals(price.getProductId());
        });

        //Assert of all previous comparisons
        assertTrue(allMatch);
    }


    @DisplayName("Fourth test required by the technical test")
    @Test
    public void testGetPriceAtSpecificTimeTest4() throws Exception {

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
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andReturn();

        //Transformation from json to Price List
        String responseContent = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        List<Prices> response = objectMapper.readValue(responseContent, new TypeReference<>() {});

        boolean allMatch = response.stream().allMatch( price -> {
            // Comparison of all values
            return  targetDateTime.isAfter(price.getStartDate()) &&
                    targetDateTime.isBefore(price.getEndDate()) &&
                    targetBrandId.equals(price.getBrandId()) &&
                    targetProductId.equals(price.getProductId());
        });

        //Assert of all previous comparisons
        assertTrue(allMatch);
    }


    @DisplayName("Fifth test required by the technical test")
    @Test
    public void testGetPriceAtSpecificTimeTest5() throws Exception {

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
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andReturn();

        //Transformation from json to Price List
        String responseContent = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        List<Prices> response = objectMapper.readValue(responseContent, new TypeReference<>() {});

        boolean allMatch = response.stream().allMatch( price -> {
            // Comparison of all values
            return  targetDateTime.isAfter(price.getStartDate()) &&
                    targetDateTime.isBefore(price.getEndDate()) &&
                    targetBrandId.equals(price.getBrandId()) &&
                    targetProductId.equals(price.getProductId());
        });

        //Assert of all previous comparisons
        assertTrue(allMatch);
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
                .andExpect(MockMvcResultMatchers.status().isNoContent());
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
                .andExpect(MockMvcResultMatchers.status().isNoContent());
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
                .andExpect(MockMvcResultMatchers.status().isNoContent());
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



}