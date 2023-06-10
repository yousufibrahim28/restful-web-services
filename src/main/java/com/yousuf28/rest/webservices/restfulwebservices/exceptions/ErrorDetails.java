package com.yousuf28.rest.webservices.restfulwebservices.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorDetails {

    private LocalDateTime timestamp;

    private String message;

    private String details;
}


