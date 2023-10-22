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

import static org.junit.jupiter.api.Assertions.*;
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

        long targetProductId = 35455L; // productId
        long targetBrandId = 1L; // brandId

        // Perform HTTP request using MockMvc
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("date", dateTimeStr)
                        .param("product", String.valueOf(targetProductId))
                        .param("brand", String.valueOf(targetBrandId)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productId").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].brandId").value(1))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        List<Prices> response = objectMapper.readValue(responseContent, new TypeReference<>() {});
        response.forEach( price -> {
            LocalDateTime startDate = price.getStartDate();
            LocalDateTime endDate = price.getEndDate();
            // Check targetDateTime is between two returned dates
            assertTrue( targetDateTime.isAfter(startDate) && targetDateTime.isBefore(endDate));
        });

    }

}