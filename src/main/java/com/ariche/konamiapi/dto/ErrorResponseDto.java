package com.ariche.konamiapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Dto for any errors that occur, will be returned to the submitter.
 */
@Data
@AllArgsConstructor
public class ErrorResponseDto {

    private String message;
    private int statusCode;
    private LocalDateTime timestamp;
}
