package com.jamesjss.retail.prices.infrastructure.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jamesjss.retail.prices.domain.model.Prices;
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

}