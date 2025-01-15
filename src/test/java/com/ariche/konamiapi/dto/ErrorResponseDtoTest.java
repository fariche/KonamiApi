package com.ariche.konamiapi.dto;

import com.ariche.konamiapi.BaseTest;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ErrorResponseDtoTest extends BaseTest {

    @Test
    public void whenErrorDtoCreated_thenNoViolations() {

        //Dto test
        Set<ConstraintViolation<GameRequestDto>> violations = validator.validate(buildGameRequestDto());
        assertTrue(violations.isEmpty());

    }
}
