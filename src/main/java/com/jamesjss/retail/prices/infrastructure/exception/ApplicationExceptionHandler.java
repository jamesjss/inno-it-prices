package com.jamesjss.retail.prices.infrastructure.exception;

import com.jamesjss.retail.prices.application.exception.PriceNotFoundException;
import com.jamesjss.retail.prices.infrastructure.exception.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, DateTimeParseException.class})
    public ResponseEntity<ErrorMessage> handleArgumentTypeMismatchException(Exception ex) {

        ErrorMessage errorMessage = ErrorMessage.builder()
                .statusCode(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .message("Argument Type Mismatch Error")
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler({PriceNotFoundException.class})
    public ResponseEntity<ErrorMessage> handlePriceNotFoundException(PriceNotFoundException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .statusCode(HttpStatus.NO_CONTENT)
                .timestamp(LocalDateTime.now())
                .message("No record found with the submitted parameters")
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NO_CONTENT);
    }


}
