package com.ariche.konamiapi;

import com.ariche.konamiapi.dto.GameRequestDto;
import com.ariche.konamiapi.dto.GameResponseDto;
import com.ariche.konamiapi.enums.GameType;

import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Common code needed for all tests.
 */
public class BaseTest {

    public static final String GAME_API_URL = "/api/games";
    public static final String TASK_COMPETE_MESSAGE = "All tasks not complete.";

    public static final int NUMBER_OF_THREADS = 10;

    public Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    public GameRequestDto buildGameRequestDto() {
        return new GameRequestDto("Test Game", GameType.SLOT);
    }

    public GameResponseDto buildGameResponseDto() {
        return new GameResponseDto(
                UUID.randomUUID().toString(),
                "Test Game",
                GameType.SLOT,
                LocalDateTime.now());
    }

    public GameRequestDto buildInvalidGameRequestDto() {
        return new GameRequestDto("", null);
    }

}
