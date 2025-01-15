package com.ariche.konamiapi.controller;

import com.ariche.konamiapi.dto.GameRequestDto;
import com.ariche.konamiapi.dto.GameResponseDto;
import com.ariche.konamiapi.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

/**
 * Game Controller for all endpoints related to creating games
 */
@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Accepts a GameRequestDto, validates and sends to service layer to process.
     * Added Swagger for documentation/testing
     * @param gameRequestDto
     * @return
     */
    @Operation(summary = "save a new game",
    description = "simulates saving a game in a database",
    responses = {
            @ApiResponse(responseCode = "201", description = "Game Created"),
            @ApiResponse(responseCode = "500", description = "Errror in Creation"),
    })
    @PostMapping
    public CompletableFuture<ResponseEntity<GameResponseDto>> saveGame(@Valid @RequestBody GameRequestDto gameRequestDto) {

        //Saves a game in the system and returns a created status code along with the response dto
        return gameService.save(gameRequestDto)
                .thenApply(gameResponseDto -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(gameResponseDto));
    }

}
