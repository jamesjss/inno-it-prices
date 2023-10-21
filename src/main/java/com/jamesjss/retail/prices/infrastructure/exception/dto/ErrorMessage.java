package com.jamesjss.retail.prices.infrastructure.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ErrorMessage {
    private HttpStatus statusCode;
    private LocalDateTime timestamp;
    private String message;
    private String details;
}
