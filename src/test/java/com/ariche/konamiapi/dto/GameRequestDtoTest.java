package com.ariche.konamiapi.dto;

import com.ariche.konamiapi.BaseTest;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameRequestDtoTest extends BaseTest {

    @Test
    public void whenRequestDtoCreated_thenNoViolations() {

        //Dto validation test
        Set<ConstraintViolation<GameRequestDto>> violations = validator.validate(buildGameRequestDto());
        assertTrue(violations.isEmpty());

    }

    @Test
    public void whenGameTypeIsNull_thenViolation() {
        Set<ConstraintViolation<GameRequestDto>> violations = validator.validate(new GameRequestDto("Valid Name", null));
        assertTrue(violations.size() == 1);
        assertTrue(violations.iterator().next().getMessage().contains("Game Type cannot be null"));
    }

}
